package filter;

import model.RoleModel;
import model.UserModel;
import service.IRoleService;
import service.impl.RoleServiceImpl;
import utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorticationFilter implements Filter {


    private ServletContext context;
    private IRoleService roleService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.roleService = new RoleServiceImpl();
    }

    /*
     * Phân quyền
     * =>User=>Role
     *
     *
     * */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        UserModel userModel = (UserModel) SessionUtil.getValue(request, "USER");
        String url = request.getRequestURI();
        if (url.startsWith("/admin")) {
            if (userModel != null) {
                RoleModel roleModel = roleService.findRoleById(userModel.getRoleId());
                if (roleModel.getRoleName().equals("ADMIN")) {
                    filterChain.doFilter(request, response);
                } else {
                    response.sendRedirect("/login?message=permissionDenied");
                }
            } else {
                response.sendRedirect("/login?message=dontLogin");
            }
        } else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
