package com.weapon.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    /**
     * 启动流程
     */
    public void run() throws InterruptedException {
        //配置服务端线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            //nettyServer的启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    //存放已完成三次握手的请求的等待队列的最大长度
                    .option(ChannelOption.SO_BACKLOG, 1024)

                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new EchoServerHandler());

                            //InboundHandler顺序执行，OutboundHandler逆序执行，
                            // 在InboundHandler2() 用pipeline或者channel的write方法，
                            // outboundHandler就会执行
                            ch.pipeline().addLast(new OutboundHandler1());
                            ch.pipeline().addLast(new InboundHandler2());
                            ch.pipeline().addLast(new OutboundHandler2());
                            ch.pipeline().addLast(new InboundHandler1());


                        }
                    });

            System.out.println("Echo 服务器启动ing");

            //启动 绑定端口异步，然后同步等待成功
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            //等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();

        } finally {
            //优雅退出，释放线程池
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();

        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        new EchoServer(port).run();

    }


}
