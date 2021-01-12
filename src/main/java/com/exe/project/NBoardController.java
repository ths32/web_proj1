package com.exe.project;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.exe.dao.NBoardDAO;
import com.exe.dao.NBoardDAO2;
import com.exe.dao.NBoardDAO3;
import com.exe.dao.NBoardDAO4;
import com.exe.dto.NBoardDTO;
import com.exe.dto.NBoardDTO2;
import com.exe.dto.NBoardDTO3;
import com.exe.dto.NBoardDTO4;
import com.exe.util.MyUtil;

@Controller
public class NBoardController {

	@Autowired
	@Qualifier("nboardDAO")
	NBoardDAO dao;

	@Autowired
	@Qualifier("nboardDAO2")
	NBoardDAO2 dao2;
	
	@Autowired
	@Qualifier("nboardDAO3")
	NBoardDAO3 dao3;
	
	@Autowired
	@Qualifier("nboardDAO4")
	NBoardDAO4 dao4;
	
	@Autowired
	MyUtil myUtil;

	
	
	
	
//-----------------------------------------------------------------------------------------------	
	 @RequestMapping(value = "/", method = RequestMethod.GET) 
	 public String home()
	 { return "index"; }
	 
//-----------------------------------------------------------------------------------------------	
	
	@RequestMapping(value = "/created.action")
	public ModelAndView created() { 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bbs/created");
		return mav;
	}
//-----------------------------------------------------------------------------------------------	

	@RequestMapping(value = "/created_ok.action", method = { RequestMethod.GET, RequestMethod.POST })
	public String created_ok(NBoardDTO dto, HttpServletRequest request, HttpServletResponse response) {
		int maxNum = dao.getMaxNum();
		dto.setNum(maxNum + 1);
		dao.insertData(dto);
		return "redirect:/list.action";
	}

	@RequestMapping(value = "/list.action", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String cp = request.getContextPath(); // +
		String pageNum = request.getParameter("pageNum");
		int currentPage = 1;
		if (pageNum != null)
			currentPage = Integer.parseInt(pageNum);
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		if (searchKey == null) {
			searchKey = "subject";
			searchValue = "";
		} else {
			if (request.getMethod().equalsIgnoreCase("GET"))
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}
		// 전체데이터갯수
		int dataCount = dao.getDataCount(searchKey, searchValue);
		// 전체페이지수
		int numPerPage = 10;
		int totalPage = myUtil.getPageCount(numPerPage, dataCount);
		if (currentPage > totalPage)
			currentPage = totalPage;
		int start = (currentPage - 1) * numPerPage + 1;
		int end = currentPage * numPerPage;
		List<NBoardDTO> lists = dao.getList(start, end, searchKey, searchValue);
		// 페이징 처리
		String param = "";
		if (!searchValue.equals("")) {
			param = "searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
		String listUrl = cp + "/list.action";
		if (!param.equals("")) {
			listUrl = listUrl + "?" + param;
		}
		String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
		// 글보기 주소 정리
		String articleUrl = cp + "/article.action?pageNum=" + currentPage;
		if (!param.equals(""))
			articleUrl = articleUrl + "&" + param;
		// 포워딩 될 페이지에 데이터를 넘긴다
		request.setAttribute("lists", lists);
		request.setAttribute("pageIndexList", pageIndexList);
		request.setAttribute("dataCount", dataCount);
		request.setAttribute("articleUrl", articleUrl);
		return "bbs/list";
	}
//---------------------------------------------------------------------------------------	
	
	  @RequestMapping(value="/article.action",method={RequestMethod.GET,RequestMethod.POST}) 
	  public ModelAndView article(HttpServletRequest request,HttpServletResponse response) throws Exception {
		  String cp = request.getContextPath(); 
		  int num = Integer.parseInt(request.getParameter("num")); 
		  String pageNum = request.getParameter("pageNum");
		  String searchKey = request.getParameter("searchKey"); 
		  String searchValue = request.getParameter("searchValue");
		  if(searchKey != null) 
			  searchValue = URLDecoder.decode(searchValue, "UTF-8");
		  //조회수 증가 
		  dao.updateHitCount(num); 
		  NBoardDTO dto = dao.getReadData(num);
		  if(dto==null){ 
			  //return "redirect:/list.action"; }
		  }
		  int lineSu = dto.getContent().split("\n").length; 
		  dto.setContent(dto.getContent().replaceAll("\n", "<br/>")); 
		  String param ="pageNum=" + pageNum; 
		  if(searchKey!=null){ 
			  param +="&searchKey=" + searchKey;
			  param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8"); 
		  }
		  ModelAndView mav = new ModelAndView(); 
		  mav.setViewName("bbs/article");
		  mav.addObject("dto",dto); 
		  mav.addObject("params",param);
		  mav.addObject("lineSu",lineSu); 
		  mav.addObject("pageNum",pageNum); 
		  return mav;
	  }
//----------------------------------------------------------------------------------

	@RequestMapping(value = "/updated.action", method = { RequestMethod.GET, RequestMethod.POST })
	public String updated(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cp = request.getContextPath();
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		NBoardDTO dto = dao.getReadData(num);
		if (dto == null) {
			return "redirect:/list.action";
		}
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		return "bbs/updated";
	}

	@RequestMapping(value = "/updated_ok.action", method = { RequestMethod.GET, RequestMethod.POST })
	public String updated_ok(NBoardDTO dto, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cp = request.getContextPath();
		String pageNum = request.getParameter("pageNum");
		dao.updateData(dto);
		return "redirect:/list.action?pageNum=" + pageNum;
	}
	
	@RequestMapping(value = "/deleted.action", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleted(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cp = request.getContextPath();
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		dao.deleteData(num);
		return "redirect:/list.action?pageNum=" + pageNum;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------
	
	//NBOARD2
	//--------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/created2.action")
	public ModelAndView created2() { 
		ModelAndView mav2 = new ModelAndView();
		mav2.setViewName("bbs/created2");
		return mav2;
	}
	//-----------------------------------------------------------------------------------------------	

	@RequestMapping(value = "/created2_ok.action", method = { RequestMethod.GET, RequestMethod.POST })
	public String created2_ok(NBoardDTO2 dto2, HttpServletRequest request2, HttpServletResponse response2) {
		int maxNum2 = dao2.getMaxNum2();
		dto2.setNum2(maxNum2 + 1);
		dao2.insertData2(dto2);
		return "redirect:/list2.action";
	}

	@RequestMapping(value = "/list2.action", method = { RequestMethod.GET, RequestMethod.POST })
	public String list2(HttpServletRequest request2, HttpServletResponse response2) throws Exception {

		String cp2 = request2.getContextPath(); // +
		String pageNum2 = request2.getParameter("pageNum2");
		int currentPage2 = 1;
		if (pageNum2 != null)
			currentPage2 = Integer.parseInt(pageNum2);
		String searchKey2 = request2.getParameter("searchKey2");
		String searchValue2 = request2.getParameter("searchValue2");

		if (searchKey2 == null) {
			searchKey2 = "subject2";
			searchValue2 = "";
		} else {
			if (request2.getMethod().equalsIgnoreCase("GET"))
				searchValue2 = URLDecoder.decode(searchValue2, "UTF-8");
		}

		// 전체데이터갯수
		int dataCount2 = dao2.getDataCount2(searchKey2, searchValue2);
		// 전체페이지수
		int numPerPage2 = 10;
		int totalPage2 = myUtil.getPageCount(numPerPage2, dataCount2);

		if (currentPage2 > totalPage2)
			currentPage2 = totalPage2;

		int start2 = (currentPage2 - 1) * numPerPage2 + 1;
		int end2 = currentPage2 * numPerPage2;

		List<NBoardDTO2> lists2 = dao2.getList2(start2, end2, searchKey2, searchValue2);
		// 페이징 처리
		String param2 = "";
		if (!searchValue2.equals("")) {
			param2 = "searchKey2=" + searchKey2;
			param2 += "&searchValue2=" + URLEncoder.encode(searchValue2, "UTF-8");
		}
		String listUrl2 = cp2 + "/list2.action";
		if (!param2.equals("")) {
			listUrl2 = listUrl2 + "?" + param2;
		}
		String pageIndexList2 = myUtil.pageIndexList2(currentPage2, totalPage2, listUrl2); //modified
		// 글보기 주소 정리
		String articleUrl2 = cp2 + "/article2.action?pageNum2=" + currentPage2;
		if (!param2.equals(""))
			articleUrl2 = articleUrl2 + "&" + param2;
		// 포워딩 될 페이지에 데이터를 넘긴다
		request2.setAttribute("lists2", lists2);
		request2.setAttribute("pageIndexList2", pageIndexList2);
		request2.setAttribute("dataCount2", dataCount2);
		request2.setAttribute("articleUrl2", articleUrl2);
		return "bbs/list2";
	}
	//---------------------------------------------------------------------------------------	
	
	  @RequestMapping(value="/article2.action",method={RequestMethod.GET,RequestMethod.POST}) 
	  public ModelAndView article2(HttpServletRequest request2,HttpServletResponse response2) throws Exception {
	  
		  String cp2 = request2.getContextPath();
	  
		  int num2 = Integer.parseInt(request2.getParameter("num2")); 
		  String pageNum2 = request2.getParameter("pageNum2");
	  
		  String searchKey2 = request2.getParameter("searchKey2"); 
		  String searchValue2 = request2.getParameter("searchValue2");
	  
		  if(searchKey2 != null) 
			  searchValue2 = URLDecoder.decode(searchValue2, "UTF-8");
	  
		  //조회수 증가 
		  dao2.updateHitCount2(num2); 
		  NBoardDTO2 dto2 = dao2.getReadData2(num2);
		  
		  if(dto2==null){ 
			  //return "redirect:/list.action"; }
		  }
		 
		  int lineSu2 = dto2.getContent2().split("\n").length; 
		  dto2.setContent2(dto2.getContent2().replaceAll("\n", "<br/>")); 
		  String param2 ="pageNum2=" + pageNum2; 
		  if(searchKey2!=null){ 
			  param2 +="&searchKey2=" + searchKey2;
			  param2 += "&searchValue2=" + URLEncoder.encode(searchValue2, "UTF-8"); 
		  }
	  
		  ModelAndView mav2 = new ModelAndView(); 
		  mav2.setViewName("bbs/article2");
		  mav2.addObject("dto2",dto2); 
		  mav2.addObject("params2",param2);
		  mav2.addObject("lineSu2",lineSu2); 
		  mav2.addObject("pageNum2",pageNum2); 
		  return mav2;
	  	}
	 
	  //----------------------------------------------------------------------------------

	  @RequestMapping(value = "/updated2.action", method = { RequestMethod.GET, RequestMethod.POST })
	  public String updated2(HttpServletRequest request2, HttpServletResponse response2) throws Exception {
		String cp2 = request2.getContextPath();
		int num2 = Integer.parseInt(request2.getParameter("num2"));
		String pageNum2 = request2.getParameter("pageNum2");
		NBoardDTO2 dto2 = dao2.getReadData2(num2);
		if (dto2 == null) {
			return "redirect:/list2.action";
		}
		request2.setAttribute("dto2", dto2);
		request2.setAttribute("pageNum2", pageNum2);

		return "bbs/updated2";
	  }

	  @RequestMapping(value = "/updated2_ok.action", method = { RequestMethod.GET, RequestMethod.POST })
	  public String updated2_ok(NBoardDTO2 dto2, HttpServletRequest request2, HttpServletResponse response2) throws Exception {
		String cp2 = request2.getContextPath();
		String pageNum2 = request2.getParameter("pageNum2");
		dao2.updateData2(dto2);

		return "redirect:/list2.action?pageNum2=" + pageNum2;
	}

	@RequestMapping(value = "/deleted2.action", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleted2(HttpServletRequest request2, HttpServletResponse response2) throws Exception {
		String cp2 = request2.getContextPath();
		String pageNum2 = request2.getParameter("pageNum2");
		int num2 = Integer.parseInt(request2.getParameter("num2"));
		dao2.deleteData2(num2);
		return "redirect:/list2.action?pageNum2=" + pageNum2;
	}
	//---------------------------------------------------------------------------------------------------------------------
	//NBOARD3
	//--------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/created3.action")
	public ModelAndView created3() { 
		ModelAndView mav3 = new ModelAndView();
		mav3.setViewName("bbs/created3");
		return mav3;
	}
	//-----------------------------------------------------------------------------------------------	

	@RequestMapping(value = "/created3_ok.action", method = { RequestMethod.GET, RequestMethod.POST })
	public String created3_ok(NBoardDTO3 dto3, HttpServletRequest request3, HttpServletResponse response3) {
		int maxNum3 = dao3.getMaxNum3();
		dto3.setNum3(maxNum3 + 1);
		dao3.insertData3(dto3);
		return "redirect:/list3.action";
	}

	@RequestMapping(value = "/list3.action", method = { RequestMethod.GET, RequestMethod.POST })
	public String list3(HttpServletRequest request3, HttpServletResponse response3) throws Exception {

		String cp3 = request3.getContextPath(); // +
		String pageNum3 = request3.getParameter("pageNum3");
		int currentPage3 = 1;
		if (pageNum3 != null)
			currentPage3 = Integer.parseInt(pageNum3);
		String searchKey3 = request3.getParameter("searchKey3");
		String searchValue3 = request3.getParameter("searchValue3");
		if (searchKey3 == null) {
			searchKey3 = "subject3";
			searchValue3 = "";
		} else {
			if (request3.getMethod().equalsIgnoreCase("GET"))
				searchValue3 = URLDecoder.decode(searchValue3, "UTF-8");
		}
		// 전체데이터갯수
		int dataCount3 = dao3.getDataCount3(searchKey3, searchValue3);

		// 전체페이지수
		int numPerPage3 = 10;
		int totalPage3 = myUtil.getPageCount(numPerPage3, dataCount3);
		if (currentPage3 > totalPage3)
			currentPage3 = totalPage3;
		int start3 = (currentPage3 - 1) * numPerPage3 + 1;
		int end3 = currentPage3 * numPerPage3;
		List<NBoardDTO3> lists3 = dao3.getList3(start3, end3, searchKey3, searchValue3);
		// 페이징 처리
		String param3 = "";
		if (!searchValue3.equals("")) {
			param3 = "searchKey3=" + searchKey3;
			param3 += "&searchValue3=" + URLEncoder.encode(searchValue3, "UTF-8");
		}
		String listUrl3 = cp3 + "/list3.action";
		if (!param3.equals("")) {
			listUrl3 = listUrl3 + "?" + param3;
		}
		String pageIndexList3 = myUtil.pageIndexList3(currentPage3, totalPage3, listUrl3); //modified
		// 글보기 주소 정리
		String articleUrl3 = cp3 + "/article3.action?pageNum3=" + currentPage3;
		if (!param3.equals(""))
			articleUrl3 = articleUrl3 + "&" + param3;
		// 포워딩 될 페이지에 데이터를 넘긴다
		request3.setAttribute("lists3", lists3);
		request3.setAttribute("pageIndexList3", pageIndexList3);
		request3.setAttribute("dataCount3", dataCount3);
		request3.setAttribute("articleUrl3", articleUrl3);

		return "bbs/list3";
	}
	//---------------------------------------------------------------------------------------	

	@RequestMapping(value="/article3.action",method={RequestMethod.GET,RequestMethod.POST}) 
	public ModelAndView article3(HttpServletRequest request3,HttpServletResponse response3) throws Exception {
  
	  String cp3 = request3.getContextPath();
	  int num3 = Integer.parseInt(request3.getParameter("num3")); 
	  String pageNum3 = request3.getParameter("pageNum3");
	  String searchKey3 = request3.getParameter("searchKey3"); 
	  String searchValue3 = request3.getParameter("searchValue3");
	  if(searchKey3 != null) 
		  searchValue3 = URLDecoder.decode(searchValue3, "UTF-8");
	  //조회수 증가 
	  dao3.updateHitCount3(num3); 
	  NBoardDTO3 dto3 = dao3.getReadData3(num3);
	  if(dto3==null){ 
		  //return "redirect:/list.action"; }
	  }
	  int lineSu3 = dto3.getContent3().split("\n").length; 
	  dto3.setContent3(dto3.getContent3().replaceAll("\n", "<br/>")); 
	  String param3 ="pageNum3=" + pageNum3; 
	  if(searchKey3!=null){ 
		  param3 +="&searchKey3=" + searchKey3;
		  param3 += "&searchValue3=" + URLEncoder.encode(searchValue3, "UTF-8"); 
	  }
	  ModelAndView mav3 = new ModelAndView(); 
	  mav3.setViewName("bbs/article3");
	  mav3.addObject("dto3",dto3); 
	  mav3.addObject("params3",param3);
	  mav3.addObject("lineSu3",lineSu3); 
	  mav3.addObject("pageNum3",pageNum3); 
	  return mav3;
	}
 
	//----------------------------------------------------------------------------------

	@RequestMapping(value = "/updated3.action", method = { RequestMethod.GET, RequestMethod.POST })
	public String updated3(HttpServletRequest request3, HttpServletResponse response3) throws Exception {
		String cp3 = request3.getContextPath();
		int num3 = Integer.parseInt(request3.getParameter("num3"));
		String pageNum3 = request3.getParameter("pageNum3");
		NBoardDTO3 dto3 = dao3.getReadData3(num3);
		if (dto3 == null) {
			return "redirect:/list3.action";
		}
		request3.setAttribute("dto3", dto3);
		request3.setAttribute("pageNum3", pageNum3);

		return "bbs/updated3";
	}

	@RequestMapping(value = "/updated3_ok.action", method = { RequestMethod.GET, RequestMethod.POST })
	public String updated3_ok(NBoardDTO3 dto3, HttpServletRequest request3, HttpServletResponse response3) throws Exception {
		String cp3 = request3.getContextPath();
		String pageNum3 = request3.getParameter("pageNum3");
		dao3.updateData3(dto3);
		
		return "redirect:/list3.action?pageNum3=" + pageNum3;
	}

	@RequestMapping(value = "/deleted3.action", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleted3(HttpServletRequest request3, HttpServletResponse response3) throws Exception {
		String cp3 = request3.getContextPath();
		String pageNum3 = request3.getParameter("pageNum3");
		int num3 = Integer.parseInt(request3.getParameter("num3"));
		dao3.deleteData3(num3);
		return "redirect:/list3.action?pageNum3=" + pageNum3;
	}
	//------------------------------------------------------------------------------------------------------------------------------
	//NBOARD4
	//------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/created4.action")
	public ModelAndView created4() { 
		ModelAndView mav4 = new ModelAndView();
		mav4.setViewName("bbs/created4");
		return mav4;
	}
	//-----------------------------------------------------------------------------------------------	

@RequestMapping(value = "/created4_ok.action", method = { RequestMethod.GET, RequestMethod.POST })
public String created4_ok(NBoardDTO4 dto4, HttpServletRequest request4, HttpServletResponse response4) {
	int maxNum4 = dao4.getMaxNum4(); //!!!
	dto4.setNum4(maxNum4 + 1);
	dao4.insertData4(dto4);
	return "redirect:/list4.action";
}

@RequestMapping(value = "/list4.action", method = { RequestMethod.GET, RequestMethod.POST })
public String list4(HttpServletRequest request4, HttpServletResponse response4) throws Exception {

	String cp4 = request4.getContextPath(); // +

	String pageNum4 = request4.getParameter("pageNum4");
	int currentPage4 = 1;

	if (pageNum4 != null)
		currentPage4 = Integer.parseInt(pageNum4);

	String searchKey4 = request4.getParameter("searchKey4");
	String searchValue4 = request4.getParameter("searchValue4");

	if (searchKey4 == null) {
		searchKey4 = "subject4";
		searchValue4 = "";
	} else {

		if (request4.getMethod().equalsIgnoreCase("GET"))
			searchValue4 = URLDecoder.decode(searchValue4, "UTF-8");
	}

	// 전체데이터갯수
	int dataCount4 = dao4.getDataCount4(searchKey4, searchValue4);

	// 전체페이지수
	int numPerPage4 = 10;
	int totalPage4 = myUtil.getPageCount(numPerPage4, dataCount4);

	if (currentPage4 > totalPage4)
		currentPage4 = totalPage4;

	int start4 = (currentPage4 - 1) * numPerPage4 + 1;
	int end4 = currentPage4 * numPerPage4;

	List<NBoardDTO4> lists4 = dao4.getList4(start4, end4, searchKey4, searchValue4);

	// 페이징 처리
	String param4 = "";
	if (!searchValue4.equals("")) {
		param4 = "searchKey4=" + searchKey4;
		param4 += "&searchValu4=" + URLEncoder.encode(searchValue4, "UTF-8");
	}

	String listUrl4 = cp4 + "/list4.action";
	if (!param4.equals("")) {
		listUrl4 = listUrl4 + "?" + param4;
	}

	String pageIndexList4 = myUtil.pageIndexList4(currentPage4, totalPage4, listUrl4);

	// 글보기 주소 정리
	String articleUrl4 = cp4 + "/article4.action?pageNum4=" + currentPage4;

	if (!param4.equals(""))
		articleUrl4 = articleUrl4 + "&" + param4;

	// 포워딩 될 페이지에 데이터를 넘긴다
	request4.setAttribute("lists4", lists4);
	request4.setAttribute("pageIndexList4", pageIndexList4);
	request4.setAttribute("dataCount4", dataCount4);
	request4.setAttribute("articleUrl4", articleUrl4);

	return "bbs/list4";
}
//---------------------------------------------------------------------------------------	


  @RequestMapping(value="/article4.action",method={RequestMethod.GET,RequestMethod.POST}) 
  public ModelAndView article4(HttpServletRequest request4,HttpServletResponse response4) throws Exception {
  
	  String cp4 = request4.getContextPath();
  
	  int num4 = Integer.parseInt(request4.getParameter("num4")); 
	  String pageNum4 = request4.getParameter("pageNum4");
  
	  String searchKey4 = request4.getParameter("searchKey4"); 
	  String searchValue4 = request4.getParameter("searchValue4");
  
	  if(searchKey4 != null) 
		  searchValue4 = URLDecoder.decode(searchValue4, "UTF-8");
  
	  //조회수 증가 
	  dao4.updateHitCount4(num4); 
	  
	  NBoardDTO4 dto4 = dao4.getReadData4(num4);
	 
	  if(dto4==null){ 
		  //return "redirect:/list.action"; }
	  }
	 
  int lineSu4 = dto4.getContent4().split("\n").length; //???
  dto4.setContent4(dto4.getContent4().replaceAll("\n", "<br/>")); 
  String param4 ="pageNum4=" + pageNum4; 
  if(searchKey4!=null){ 
	  param4 +="&searchKey4=" + searchKey4;
	  param4 += "&searchValue4=" + URLEncoder.encode(searchValue4, "UTF-8"); 
  }
  
  ModelAndView mav4 = new ModelAndView(); 
  mav4.setViewName("bbs/article4");
  mav4.addObject("dto4",dto4); 
  mav4.addObject("params4",param4);
  mav4.addObject("lineSu4",lineSu4); 
  mav4.addObject("pageNum4",pageNum4); 
  return mav4;
  }
 
  
//----------------------------------------------------------------------------------

@RequestMapping(value = "/updated4.action", method = { RequestMethod.GET, RequestMethod.POST })
public String updated4(HttpServletRequest request4, HttpServletResponse response4) throws Exception {
	String cp4 = request4.getContextPath();

	int num4 = Integer.parseInt(request4.getParameter("num4"));
	String pageNum4 = request4.getParameter("pageNum4");

	NBoardDTO4 dto4 = dao4.getReadData4(num4);

	if (dto4 == null) {
		return "redirect:/list4.action";
	}

	request4.setAttribute("dto4", dto4);
	request4.setAttribute("pageNum4", pageNum4);

	return "bbs/updated4";
}

@RequestMapping(value = "/updated4_ok.action", method = { RequestMethod.GET, RequestMethod.POST })
public String updated4_ok(NBoardDTO4 dto4, HttpServletRequest request4, HttpServletResponse response4) throws Exception {
	String cp4 = request4.getContextPath();

	String pageNum4 = request4.getParameter("pageNum4");

	dao4.updateData4(dto4);

	return "redirect:/list4.action?pageNum4=" + pageNum4;
}

@RequestMapping(value = "/deleted4.action", method = { RequestMethod.GET, RequestMethod.POST })
public String deleted4(HttpServletRequest request4, HttpServletResponse response4) throws Exception {
	String cp4 = request4.getContextPath();
	String pageNum4 = request4.getParameter("pageNum4");
	int num4 = Integer.parseInt(request4.getParameter("num4"));
	dao4.deleteData4(num4);
	return "redirect:/list4.action?pageNum4=" + pageNum4;
}
	
	
}
