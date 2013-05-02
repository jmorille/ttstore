package eu.ttbox.config.model {
    import org.granite.validation.constraints.NotNull;


    /**
     * For Flex Mapping @see  http://code.google.com/p/flexxb/wiki/Usage
     */
    [Entity]
    [Bindable]
    [XmlClass(alias="ttbox")]
    public class TTBoxConfigModel {

        [XmlElement]
        public var cashregisterId:String;

        [XmlElement] 
        public var connector:ConnexionConfig=new ConnexionConfig();

        public function TTBoxConfigModel() {
        }



    }
}

