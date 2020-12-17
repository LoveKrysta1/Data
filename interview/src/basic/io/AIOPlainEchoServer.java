package basic.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AIOPlainEchoServer {
    public void serve(int port) throws IOException {
        System.out.println("Listening for connections on port " + port);
        final AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(port);
        //将ServerSocket绑定到指定的端口里
        serverChannel.bind(address);
        final CountDownLatch latch = new CountDownLatch(1);
        //开始接收新的客户端请求，一旦一个客户端请求被接收， CompletionHandler 就会被调用
        serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(final AsynchronousSocketChannel channel, Object attachment) {
                //一旦完成处理，再次接收新的客户端请求
                serverChannel.accept(null, this);
                ByteBuffer buffer = ByteBuffer.allocate(100);
                //在channel里植入一个读操作EchoCompletionHandler,一旦buffer有数据写入,EchoCompletionHandler便会被唤醒
                channel.read(buffer, buffer, new EchoCompletionHandler(channel));

            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                try {
                    //若遇到异常，关闭channel
                    serverChannel.close();
                } catch (IOException e) {
                    //ignore on close
                } finally {
                    latch.countDown();
                }
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private final class EchoCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
        private AsynchronousSocketChannel channel;

        EchoCompletionHandler(AsynchronousSocketChannel channel) {
            this.channel = channel;
        }

        @Override
        public void completed(Integer result, ByteBuffer buffer) {
            buffer.flip();
            //在channel里植入一个写操作CompletionHandler
            channel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer buffer) {
                    if (buffer.hasRemaining()) {
                        //如果buffer里还有内容，则再次触发写入操作将buffer里的内容写入channel
                        channel.write(buffer, buffer, this);
                    } else {
                        buffer.compact();
                        //如果channel里还有内容需要读入到buffer里就再次触发
                        channel.read(buffer, buffer, EchoCompletionHandler.this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                        //ignore on close
                    }
                }
            });
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            try {
                //若遇到异常，关闭channel
                channel.close();
            } catch (IOException e) {
                //ignore on close
            }
        }
    }

}
