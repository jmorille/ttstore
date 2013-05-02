

import org.fusesource.scalate.servlet.ServletRenderContext._
import org.fusesource.scalate.support.TemplatePackage
import org.fusesource.scalate.{Binding, TemplateSource}
import eu.ttbox.model.ui.store.WebSite
import eu.ttbox.store.web.filter.WebSiteSelectorFilter

/**
 * Defines some common imports, attributes and methods across templates in package foo and below
 */
class ScalatePackage extends TemplatePackage {

  def header(template: TemplateSource, bindings: List[Binding]) = """

      val webSite : eu.ttbox.model.ui.store.WebSite = request.getAttribute(eu.ttbox.store.web.filter.WebSiteSelectorFilter.WEBSITE_KEY).asInstanceOf[eu.ttbox.model.ui.store.WebSite]
      def foo = "bar"
   """

}
