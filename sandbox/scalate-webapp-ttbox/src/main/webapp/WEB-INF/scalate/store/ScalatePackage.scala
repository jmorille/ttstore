import org.fusesource.scalate.support.TemplatePackage
import org.fusesource.scalate.{Binding, TemplateSource}


/**
 * Defines some common imports, attributes and methods across templates in package foo and below
 */
class ScalatePackage extends TemplatePackage {

  def header(template: TemplateSource, bindings: List[Binding]) = """

      def foo = "bar"
      def time = new java.util.Date()
  """

}
