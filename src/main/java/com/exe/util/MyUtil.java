package com.exe.util;

public class MyUtil {
	
	//전체 페이지수 구하기
	//numPerPage : 한화면에 표시할 데이터의 갯수
	//dataCount : 전체 데이터의 갯수
	public int getPageCount(int numPerPage, int dataCount){
		
		int pageCount = 0;
		pageCount = dataCount / numPerPage;
		
		if(dataCount % numPerPage != 0)
			pageCount++;
		
		return pageCount;	
		
	}
	
	//페이징 처리 메소드
	//currentPage :현재 표시할 페이지
	//totalPage : 전체 페이지수
	//listUrl : 링크를 설정할 url
	public String pageIndexList(int currentPage, int totalPage, String listUrl){
		
		int numPerBlock = 5; //1◀이전 6 7 8 9 10 다음▶11(6-10까지 표시되는 페이지 갯수)
		int currentPageSetup; //표시할 첫 페이지(6)의 – 1 해준 값(5,10,15,20...)
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		if(currentPage==0 || totalPage==0)	//데이터가 없을 경우
			return "";
		
		//abc.jsp?a=1
		if(listUrl.indexOf("?") != -1)  //주소줄에 ?표가 있다면
			listUrl = listUrl + "&";
		else
			listUrl = listUrl + "?";
		
		//표시할 첫 페이지의 – 1 해준 값
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		
		if(currentPage % numPerBlock == 0)
			currentPageSetup = currentPageSetup - numPerBlock;
		
		//◀이전
		if(totalPage > numPerBlock && currentPageSetup > 0){
						
			sb.append("<a href=\"" + listUrl + "pageNum=" 
					+ currentPageSetup + "\">◀이전</a>&nbsp;");
			
		}
		
		//바로가기 페이지
		page = currentPageSetup + 1;
		
		while(page <= totalPage && page <= (currentPageSetup + numPerBlock)){
			
			if(page == currentPage){				
				
				sb.append("<font color=\"Fuchsia\">" + page + "</font>&nbsp;");				
			
			}else{
				
				sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">"
						+ page + "</a>&nbsp;");
				
			}
			
			page++;
			
		}		
		
		//다음▶
		if(totalPage - currentPageSetup > numPerBlock){
						
			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">다음▶</a>&nbsp;");
			
		}
		
		
		return sb.toString();
		
	}
	
	
	  //nboard2-------------------------------------------------------------------------------------------------------------------- 
	  //전체 페이지수 구하기 
	  //numPerPage : 한 화면에 표시할 데이터의 갯수 
	  //dataCount : 전체 데이터의 갯수 
	  public int getPageCount2(int numPerPage2, int dataCount2){
	  
	  int pageCount2 = 0; pageCount2 = dataCount2 / numPerPage2;
	  
	  if(dataCount2 % numPerPage2 != 0) pageCount2++;
	  
	  return pageCount2;
	  
	  }
	  
	  //페이징 처리 메소드 
	  //currentPage :현재 표시할 페이지 
	  //totalPage : 전체 페이지수 
	  //listUrl : 링크를 설정할 url
	  public String pageIndexList2(int currentPage2, int totalPage2, String listUrl2){
	  
		  int numPerBlock2 = 5; //1◀이전 6 7 8 9 10 다음▶11(6-10까지 표시되는 페이지 갯수) 
		  int currentPageSetup2; //표시할 첫 페이지(6)의 – 1 해준 값(5,10,15,20...) 
		  int page2;
	  
		  StringBuffer sb2 = new StringBuffer();
	  
	  if(currentPage2==0 || totalPage2==0) //데이터가 없을 경우 
		  return "";
	  
	  //abc.jsp?a=1 
	  if(listUrl2.indexOf("?") != -1) //주소줄에 ?표가 있다면 
		  listUrl2 = listUrl2 + "&"; 
	  else listUrl2 = listUrl2 + "?";
	  
	  //표시할 첫 페이지의 – 1 해준 값 
	  currentPageSetup2 = (currentPage2/numPerBlock2)*numPerBlock2;
	  
	  if(currentPage2 % numPerBlock2 == 0) 
		  currentPageSetup2 = currentPageSetup2 - numPerBlock2;
	  
	  //◀이전 
	  if(totalPage2 > numPerBlock2 && currentPageSetup2 > 0){
	    sb2.append("<a href=\"" + listUrl2 + "pageNum2=" + currentPageSetup2 + "\">◀이전</a>&nbsp;");
	  }
	  
	  //바로가기 페이지 
	  page2 = currentPageSetup2 + 1;
	  while(page2 <= totalPage2 && page2 <= (currentPageSetup2 + numPerBlock2)){
	  
		  if(page2 == currentPage2){
			  sb2.append("<font color=\"Fuchsia\">" + page2 + "</font>&nbsp;");
		  }else{
			  sb2.append("<a href=\"" + listUrl2 + "pageNum2=" + page2 + "\">" + page2 +"</a>&nbsp;");
		  }
		  page2++;
	  }
	  
	  //다음▶
	  if(totalPage2 - currentPageSetup2 > numPerBlock2){
	    sb2.append("<a href=\"" + listUrl2 + "pageNum2=" + page2 + "\">다음▶</a>&nbsp;");
	  }
	  
	  return sb2.toString();
	  } 
	  
	  	//nboard3---------------------------------------------------------------------------------------------------------------------
	  	//전체 페이지수 구하기
		//numPerPage : 한화면에 표시할 데이터의 갯수
		//dataCount : 전체 데이터의 갯수
		public int getPageCount3(int numPerPage3, int dataCount3){
			
			int pageCount3 = 0;
			pageCount3 = dataCount3 / numPerPage3;
			
			if(dataCount3 % numPerPage3 != 0)
				pageCount3++;
			
			return pageCount3;	
			
		}
		
		//페이징 처리 메소드
		//currentPage :현재 표시할 페이지
		//totalPage : 전체 페이지수
		//listUrl : 링크를 설정할 url
		public String pageIndexList3(int currentPage3, int totalPage3, String listUrl3){
			
			int numPerBlock3 = 5; //1◀이전 6 7 8 9 10 다음▶11(6-10까지 표시되는 페이지 갯수)
			int currentPageSetup3; //표시할 첫 페이지(6)의 – 1 해준 값(5,10,15,20...)
			int page3;
			
			StringBuffer sb3 = new StringBuffer();
			
			if(currentPage3==0 || totalPage3==0)	//데이터가 없을 경우
				return "";
			
			//abc.jsp?a=1
			if(listUrl3.indexOf("?") != -1)  //주소줄에 ?표가 있다면
				listUrl3 = listUrl3 + "&";
			else
				listUrl3 = listUrl3 + "?";
			
			//표시할 첫 페이지의 – 1 해준 값
			currentPageSetup3 = (currentPage3/numPerBlock3)*numPerBlock3;
			
			if(currentPage3 % numPerBlock3 == 0)
				currentPageSetup3 = currentPageSetup3 - numPerBlock3;
			
			//◀이전
			if(totalPage3 > numPerBlock3 && currentPageSetup3 > 0){
							
				sb3.append("<a href=\"" + listUrl3 + "pageNum3=" + currentPageSetup3 + "\">◀이전</a>&nbsp;");
				
			}
			
			//바로가기 페이지
			page3 = currentPageSetup3 + 1;
			
			while(page3 <= totalPage3 && page3 <= (currentPageSetup3 + numPerBlock3)){
				
				if(page3 == currentPage3){				
					
					sb3.append("<font color=\"Fuchsia\">" + page3 + "</font>&nbsp;");				
				
				}else{
					
					sb3.append("<a href=\"" + listUrl3 + "pageNum3=" + page3 + "\">"
							+ page3 + "</a>&nbsp;");
					
				}
				
				page3++;
				
			}		
			
			//다음▶
			if(totalPage3 - currentPageSetup3 > numPerBlock3){
							
				sb3.append("<a href=\"" + listUrl3 + "pageNum3=" + page3 + "\">다음▶</a>&nbsp;");
				
			}
			
			
			return sb3.toString();
			
		}
		
		
	//nboard4-------------------------------------------------------------------------------------------------------------------
		//전체 페이지수 구하기
		//numPerPage : 한화면에 표시할 데이터의 갯수
		//dataCount : 전체 데이터의 갯수
		public int getPageCount4(int numPerPage4, int dataCount4){
			
			int pageCount4 = 0;
			pageCount4 = dataCount4 / numPerPage4;
			
			if(dataCount4 % numPerPage4 != 0)
				pageCount4++;
			
			return pageCount4;	
			
		}
		
		//페이징 처리 메소드
		//currentPage :현재 표시할 페이지
		//totalPage : 전체 페이지수
		//listUrl : 링크를 설정할 url
		public String pageIndexList4(int currentPage4, int totalPage4, String listUrl4){
			
			int numPerBlock4 = 5; //1◀이전 6 7 8 9 10 다음▶11(6-10까지 표시되는 페이지 갯수)
			int currentPageSetup4; //표시할 첫 페이지(6)의 – 1 해준 값(5,10,15,20...)
			int page4;
			
			StringBuffer sb4 = new StringBuffer();
			
			if(currentPage4==0 || totalPage4==0)	//데이터가 없을 경우
				return "";
			
			//abc.jsp?a=1
			if(listUrl4.indexOf("?") != -1)  //주소줄에 ?표가 있다면
				listUrl4 = listUrl4 + "&";
			else
				listUrl4 = listUrl4 + "?";
			
			//표시할 첫 페이지의 – 1 해준 값
			currentPageSetup4 = (currentPage4/numPerBlock4)*numPerBlock4;
			
			if(currentPage4 % numPerBlock4 == 0)
				currentPageSetup4 = currentPageSetup4 - numPerBlock4;
			
			//◀이전
			if(totalPage4 > numPerBlock4 && currentPageSetup4 > 0){
							
				sb4.append("<a href=\"" + listUrl4 + "pageNum4=" 
						+ currentPageSetup4 + "\">◀이전</a>&nbsp;");
				
			}
			
			//바로가기 페이지
			page4 = currentPageSetup4 + 1;
			
			while(page4 <= totalPage4 && page4 <= (currentPageSetup4 + numPerBlock4)){
				
				if(page4 == currentPage4){				
					
					sb4.append("<font color=\"Fuchsia\">" + page4 + "</font>&nbsp;");				
				
				}else{
					
					sb4.append("<a href=\"" + listUrl4 + "pageNum4=" + page4 + "\">"
							+ page4 + "</a>&nbsp;");
					
				}
				
				page4++;
				
			}		
			
			//다음▶
			if(totalPage4 - currentPageSetup4 > numPerBlock4){
							
				sb4.append("<a href=\"" + listUrl4 + "pageNum4=" + page4 + "\">다음▶</a>&nbsp;");
				
			}
			
			
			return sb4.toString();
			
		}
		
		
		
		
}
