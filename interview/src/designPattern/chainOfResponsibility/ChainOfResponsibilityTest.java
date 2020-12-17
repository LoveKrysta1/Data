package designPattern.chainOfResponsibility;

public class ChainOfResponsibilityTest {
    public static void main(String[] args) {
        Request request = new Request.RequestBuilder().loggedOn(true).frequentOk(true).build();

        RequestFrequentHandler requestFrequentHandler = new RequestFrequentHandler(new LoggingHandler(new RequestFrequentHandler(null)));

        if (requestFrequentHandler.process(request)) {
            System.out.println("正常业务处理");
        } else {
            System.out.println("访问异常");
        }
    }
}

class Request {
    private boolean loggedOn;
    private boolean frequentOk;
    private boolean isPermits;
    private boolean containSensitiveWords;
    private String requestBody;

    public Request(boolean loggedOn, boolean frequentOk, boolean isPermits, boolean containSensitiveWords) {
        this.loggedOn = loggedOn;
        this.frequentOk = frequentOk;
        this.isPermits = isPermits;
        this.containSensitiveWords = containSensitiveWords;
    }

    static class RequestBuilder {
        private boolean loggedOn;
        private boolean frequentOk;
        private boolean isPermits;
        private boolean containsSensitiveWords;

        RequestBuilder loggedOn(boolean loggedOn) {
            this.loggedOn = loggedOn;
            return this;
        }

        RequestBuilder frequentOk(boolean frequentOk) {
            this.frequentOk = frequentOk;
            return this;
        }

        RequestBuilder isPermits(boolean isPermits) {
            this.isPermits = isPermits;
            return this;
        }

        public Request build() {
            Request request = new Request(loggedOn, frequentOk, isPermits, containsSensitiveWords);
            return request;
        }
    }

    public boolean isLoggedOn() {
        return loggedOn;
    }

    public boolean isFrequentOk() {
        return frequentOk;
    }

    public boolean isPermits() {
        return isPermits;
    }

    public boolean isContainSensitiveWords() {
        return containSensitiveWords;
    }

}

abstract class Handler {
    Handler next;

    public Handler(Handler next) {
        this.next = next;
    }

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    abstract boolean process(Request request);
}

class RequestFrequentHandler extends Handler {
    public RequestFrequentHandler(Handler next) {
        super(next);
    }

    @Override
    boolean process(Request request) {
        System.out.println("访问频率控制.");
        if (request.isFrequentOk()) {
            Handler next = getNext();
            if (null == next) {
                return true;
            }
            if (!next.process(request)) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}

class LoggingHandler extends Handler {

    public LoggingHandler(Handler next) {
        super(next);
    }

    @Override
    boolean process(Request request) {
        System.out.println("登录验证");
        if (request.isLoggedOn()) {
            Handler next = getNext();
            if (null == next) {
                return true;
            }
            if (!next.process(request)) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


}