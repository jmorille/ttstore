

# ############################ #
# ## ### Config Eclipse ### ## # 
# ############################ #

# GWT Plugins
# ########################
http://dl.google.com/eclipse/plugin/3.7

# m2e Intégration avec GWT
# ########################
Source : https://github.com/gwt-maven-plugin/
Update Site nightly : http://gwt-maven-plugin.github.com/m2e-gwt-update-site/nightly
Update Site release (ne cherchez pas, aucune encore) : http://gwt-maven-plugin.github.com/m2e-gwt-update-site/releases

# m2e Intégration WTP
# ###################
Update Site : http://download.jboss.org/jbosstools/updates/m2eclipse-wtp/


# Request Factory : http://code.google.com/p/google-web-toolkit/wiki/RequestFactoryInterfaceValidation
# #################
Enable Java Compiler -> Annotation Processing
Add options : verbose = false
In Factory path add : requestfactory-apt.jat

# m2e build-helper
# ################
To install it, go to Window > Preferences > Maven > Discovery > Open Catalog and install the buildhelper connector, then re-import the project from scratch.
 
 
# ############################ #
# ## ### Doc and Other  ### ## # 
# ############################ #


# GWT maven plugin
# ################
Doc : http://mojo.codehaus.org/gwt-maven-plugin/index.html


# m2e Intégration avec maven-war plugin
# #####################################
Doc : https://docs.sonatype.org/display/M2ECLIPSE/Integration+with+Maven+WAR+Plugin
Update Site : http://m2eclipse.sonatype.org/sites/m2e-webby/



