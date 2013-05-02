package eu.ttbox.config.model {
    import com.googlecode.flexxb.annotation.XmlAttribute;

    import org.granite.validation.constraints.NotNull;

    [Entity]
    [Bindable]
    [XmlClass(ordered="true")]
    public class ConnexionConfig {

        [NotNull]
        [XmlElement(alias="protocol", order="1")]
        public var protocol:String="http";

        [NotNull]
        [XmlElement(alias="host", order="2")]
        public var serverHost:String="localhost";

        [NotNull]
        [XmlElement(alias="port", order="3")]
        public var serverPort:String="8080";


        [NotNull]
        [XmlElement(alias="contextRoot", order="4")]
        public var contextRoot:String="/ttbox";

        public function ConnexionConfig() {
        }

    }
}

