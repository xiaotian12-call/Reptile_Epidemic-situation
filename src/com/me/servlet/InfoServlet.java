package com.me.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.me.dao.InfoDao;
import com.me.domain.City;
import com.me.domain.Info;
import com.me.domain.Provinces;

/**
 * 
 * @author 王正帅
 * @date: 2020年3月3日 下午3:51:12 
 *
 */
@WebServlet("/info")
public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    InfoDao dao = new InfoDao();
    public InfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if(method.equals("bg")) {
			try {
				bg(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(method.equals("tu")) {
			try {
				tu(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(method.equals("city")) {
			try {
				city(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(method.equals("d")) {
			try {
				d(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(method.equals("yiqing")) {
			try {
				yiqing(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(method.equals("city2")) {
			try {
				city2(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(method.equals("d2")) {
			try {
				d2(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());yiqing
	}
	
	/**
	 * @param request
	 * @param response
	 */
	private void city2(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		String province = request.getParameter("province");
		Provinces provinces = dao.getProvinces(province);
		request.setAttribute("id", provinces.getId());
		request.getRequestDispatcher("city.jsp").forward(request, response);
	}
	
	/**
	 * @param request
	 * @param response
	 */
	private void yiqing(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException {
		List<Provinces> list = dao.getList4();
		Gson gson = new Gson();
		String json = gson.toJson(list);
		request.getSession().setAttribute("list", list);
		response.getWriter().write(json);
	}

	/**
	 * @param request
	 * @param response
	 */
	private void d2(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException {
		String id = request.getParameter("id");
		String id_str = "";
		id_str +=  id.indexOf(0)+id.indexOf(1);
		List<Provinces> list = dao.getListC2(id_str);
		List<City> data = new ArrayList<City>();
		for(int i=0; i<list.size();i++) {
			City city = new City();
			city.setName(list.get(i).getName());
			city.setValue(Integer.parseInt(list.get(i).getConfirm()));
			data.add(city);
		}
		Gson gson = new Gson();
		String json = gson.toJson(data);
		System.out.println(json);
		response.getWriter().write(json);
	}
	
	/**
	 * @param request
	 * @param response
	 */
	private void d(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		String province = request.getParameter("province");
		String time = "2020-02-12 10:14:15";
		List<Info> list = dao.getListC(time,province);
		List<City> data = new ArrayList<City>();
		for(int i=1; i<list.size();i++) {
			City city = new City();
			city.setName(list.get(i).getCity());
			city.setValue(Integer.parseInt(list.get(i).getConfirmed_num()));
			data.add(city);
		}
		Gson gson = new Gson();
		String json = gson.toJson(data);
		System.out.println(json);
		response.getWriter().write(json);
	}

	/**
	 * @param request
	 * @param response
	 */
	private void city(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		String province = request.getParameter("province");
		String time = "2020-02-12 10:14:15";
		List<Info> list = dao.getListC(time,province);
		List<City> data = new ArrayList<City>();
		for(int i=1; i<list.size();i++) {
			City city = new City();
			city.setName(list.get(i).getCity());
			city.setValue(Integer.parseInt(list.get(i).getConfirmed_num()));
			data.add(city);
		}
		Gson gson = new Gson();
		String json = gson.toJson(data);
		System.out.println(json);
		request.setAttribute("list", json);
		request.setAttribute("province", province);
		request.getRequestDispatcher("city.jsp").forward(request, response);
	}

	/**
	 * @param request
	 * @param response
	 */
	private void tu(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException {
		String time = request.getParameter("time");
		List<Info> list = dao.getListT(time);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		//System.out.println(json);
		request.getSession().setAttribute("list", list);
		response.getWriter().write(json);
	}

	/**
	 * @param request
	 * @param response
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void bg(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String time = request.getParameter("time");
		List<Info> list = dao.getList(time);
		request.setAttribute("list", list);
		if(list!=null) {
			System.out.println(list.get(0).getDate());
			request.getRequestDispatcher("di.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
