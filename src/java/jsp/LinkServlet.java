/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utillies.BaseSQLHttpServlet;

/**
 *
 * @author guanshiqian
 */
public class LinkServlet extends BaseSQLHttpServlet {

    @Override
    public String getSQL(HttpServletRequest request, HttpServletResponse response) {
        return "SELECT * FROM WLXX WHERE ROWNUM<=10";
    }
}
