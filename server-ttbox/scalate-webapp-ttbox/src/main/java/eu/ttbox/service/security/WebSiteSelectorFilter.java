package eu.ttbox.service.security;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import eu.ttbox.model.ui.store.WebSite;
import eu.ttbox.model.ui.store.WebSiteAccessMode;

@Service("webSiteSelectorFilter")
public class WebSiteSelectorFilter extends GenericFilterBean {

    public static final Logger log = LoggerFactory.getLogger(WebSiteSelectorFilter.class);

    public static final String WEBSITE_HOST_KEY = WebSiteSelectorFilter.class.getName() + ".webSite.hostname";
    public static final String WEBSITE_ID_KEY = WebSiteSelectorFilter.class.getName() + ".webSite.id";

    public static final String WEBSITE_KEY = "webSite";

    public static final String DEFAULT_PERSISTENCE_MANAGER_FACTORY_BEAN_NAME = "entityManagerFactory";

    private String entityManagerFactoryBeanName = DEFAULT_PERSISTENCE_MANAGER_FACTORY_BEAN_NAME;



    /**
     * Set the bean name of the EntityManagerFactory to fetch from Spring's
     * root application context. Default is "entityManagerFactory".
     *
     * @param entityManagerFactoryBeanName The BeanName of the EntityManagerFactory
     * @see #DEFAULT_PERSISTENCE_MANAGER_FACTORY_BEAN_NAME
     */
    public void setEntityManagerFactoryBeanName(String entityManagerFactoryBeanName) {
        this.entityManagerFactoryBeanName = entityManagerFactoryBeanName;
    }

    /**
     * Return the bean name of the EntityManagerFactory to fetch from Spring's
     * root application context.
     *
     * @return The BeanName of the EntityManagerFactory
     */
    protected String getEntityManagerFactoryBeanName() {
        return this.entityManagerFactoryBeanName;
    }

    /**
     * Look up the EntityManagerFactory that this filter should use.
     * The default implementation looks for a bean with the specified name
     * in Spring's root application context.
     *
     * @return the EntityManagerFactory to use
     * @see #getEntityManagerFactoryBeanName
     */
    protected EntityManagerFactory lookupEntityManagerFactory() {
        log.debug("Using EntityManagerFactory '{}' for WebSiteSelectorFilter", getEntityManagerFactoryBeanName());
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        return wac.getBean(getEntityManagerFactoryBeanName(), EntityManagerFactory.class);
    }

    protected EntityManager getEntityManager() {
        EntityManagerFactory emf = lookupEntityManagerFactory();
        EntityManagerHolder entityManagerHolder = (EntityManagerHolder) TransactionSynchronizationManager.getResource(emf);
        EntityManager entityManager = entityManagerHolder.getEntityManager();
        return entityManager;
    }

    private WebSite findWebSiteByHostname(String hostname) {
        WebSite webSite = null;
        // Locate entitymanger
        EntityManager entityManager = getEntityManager();
        // Search WebSite
        TypedQuery<WebSite> query = entityManager.createNamedQuery("WebSite.findByHost", WebSite.class);
        query.setParameter("hostname", hostname);
        List<WebSite> sites = query.getResultList();
        if (sites.size() > 0) {
            webSite = sites.get(0);
        }
        return webSite;
    }

    private WebSite findWebSiteById(Integer id) {
        EntityManager entityManager = getEntityManager();
        WebSite webSite = (WebSite) entityManager.find(WebSite.class, id);
        return webSite;
    }

    private WebSite getWebSiteFronRequest(HttpServletRequest request) {
        WebSite webSite = null;
        Integer webSiteId = null;
        String hostName = request.getServerName();
        HttpSession session = request.getSession();
        String sessionHostname = (String) session.getAttribute(WEBSITE_HOST_KEY);
        if (sessionHostname != null && sessionHostname.equals(hostName)) {
            Integer sessionWebSiteId = (Integer) session.getAttribute(WEBSITE_ID_KEY);
            if (sessionWebSiteId != null) {
               webSite = findWebSiteById(sessionWebSiteId);
            } 
        }
        if (webSite == null) {
            webSite = findWebSiteByHostname(hostName);
        }
        if (webSite != null) {
            webSiteId = webSite.getId();
        }
        session.setAttribute(WEBSITE_HOST_KEY, hostName);
        session.setAttribute(WEBSITE_ID_KEY, webSiteId);
        return webSite;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // Read Request
        String hostName = request.getServerName();
        WebSite webSite = getWebSiteFronRequest(request);
        log.debug("Associated Hostname {} = WebSite {} ", hostName, webSite);
        if (webSite != null) {
            // Define Attribute to request
            request.setAttribute(WEBSITE_KEY, webSite);
            // Check Status
            boolean isActif = webSite.getStatus().isActif();
            if (isActif) {
                WebSiteAccessMode acccesMode = webSite.getAccessMode();

            }
        }
       //TODO  response.sendRedirect();
        chain.doFilter(request, response);
    }
}
