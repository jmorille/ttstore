package eu.ttbox.config {
    import mx.messaging.messages.ErrorMessage;

    import org.granite.tide.BaseContext;
    import org.granite.tide.IExceptionHandler;

    public class SecurityExceptionHandler implements IExceptionHandler {
        public static const SECURITY:String="Server.Security.";

        public static const NOT_LOGGED_IN:String="Server.Security.NotLoggedIn";

        public static const SESSION_EXPIRED:String="Server.Security.SessionExpired";

        public static const ACCESS_DENIED:String="Server.Security.AccessDenied";

        public function SecurityExceptionHandler() {
        }


        public function accepts(emsg:ErrorMessage):Boolean {
            return emsg.faultCode.search(SECURITY) == 0;
        }

        public function handle(context:BaseContext, emsg:ErrorMessage):void {
            switch (emsg.faultCode) {
                case "Server.Security.InvalidCredentials":
                case "Channel.Authentication.Error":
                    // type=SecurityEvent.INVALID_CREDENTIALS;
                    break;
                case NOT_LOGGED_IN:
                    // type=SecurityEvent.NOT_LOGGED_IN;
                    break;
                case SESSION_EXPIRED:
                // type=SecurityEvent.SESSION_EXPIRED;
                case ACCESS_DENIED:
                    // type=SecurityEvent.ACCESS_DENIED;
                    if (context.identity.loggedIn) {
                        context.statusMessages.clearMessages();
                        context.statusMessages.addMessage("WARN", "Session expired, please log in");
                        context.identity.loggedIn=false;
                    }
                    break;
                default:
                    trace("Unknown security fault code: ", emsg.faultCode);
            }

        }


    }
}