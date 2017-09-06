<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/adminProduct?method=addUI";
			}
		</script>
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>商品列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="right">
							<button type="button" id="add" name="add" value="添加" class="button_add" onclick="addProduct()">
添加商品
</button>

						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="18%">
										序号
									</td>
									<td align="center" width="17%">
										商品图片
									</td>
									<td align="center" width="17%">
										商品名称
									</td>
									<td align="center" width="17%">
										商品价格
									</td>
									<td align="center" width="17%">
										是否热门
									</td>
									<td width="7%" align="center">
										编辑
									</td>
									<td width="7%" align="center">
										删除
									</td>
								</tr>
								<c:forEach items="${pb.list }" var="p" varStatus="vs">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="18%">
												${vs.count }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<img width="40" height="45" src="${ pageContext.request.contextPath }/${p.pimage}">
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												${p.pname }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												${p.shop_price }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<c:if test="${p.is_hot==1 }">是</c:if>
												<c:if test="${p.is_hot!=1 }">否</c:if>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${ pageContext.request.contextPath }/adminProduct?.method=addUI&pid=${p.pid}">
													<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<a href="${ pageContext.request.contextPath }/adminProduct_delete.action?pid=">
													<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
											</td>
										</tr>
									</c:forEach>
							</table>
							
							<!--分页 -->
							<div style="width:380px;margin:0 auto;margin-top:50px;">
								<ul class="pagination" style="text-align:center; margin-top:10px;">
			
			
								<!-- 判断当前页是否是首页 -->
			  					  <c:if test="${pb.currPage==1 }">
									<li class="disabled">
									     <a href="javascript:void(0)" aria-label="Previous">
								    	 <span aria-hidden="true">&laquo;</span></a>
									</li>
								  </c:if>
				
								  <c:if test="${pb.currPage!=1 }">
									<li>
					     				<a href="${pageContext.request.contextPath}/adminProduct?method=findAll&currPage=${pb.currPage-1}" aria-label="Previous">
				    					 <span aria-hidden="true">&laquo;</span></a>
									</li>
								  </c:if>
							<!-- 判断当前页是否是首页 -->
									
									
							<!-- 展示所有页面 -->
									<c:forEach begin="${pb.currPage-5>0?pb.currPage-5:1 }" end="${pb.currPage+4>pb.totalPage?pb.totalPage:pb.currPage+4 }" var="n">
									<!-- 判断是否是当前页 -->
										<c:if test="${pb.currPage==n }">
										  <li class="active"><a href="javascript:void(0)">${n }</a></li>
										</c:if>
										<c:if test="${pb.currPage!=n }">
										  <li><a href="${pageContext.request.contextPath}/adminProduct?method=findAll&currPage=${n}">${n }</a></li>
										</c:if> 
									</c:forEach>
									
									
							<!-- 判断是否是最后一页-->
									<c:if test="${pb.currPage==pb.totalPage }">
									<li class="disabled">
										<a href="javascript:void(0)" aria-label="Next">
											<span aria-hidden="true">&raquo;</span>
										</a>
									</li>
									</c:if>
									
									
									<c:if test="${pb.currPage!=pb.totalPage}">
									<li>
										<a href="${pageContext.request.contextPath}/adminProduct?method=findAll&currPage=${pb.currPage+1}" aria-label="Next">
											<span aria-hidden="true">&raquo;</span>
										</a>
									</li>
									</c:if>
							<!-- 判断是否是最后一页-->
								</ul>
							</div>
					<!-- 		 分页结束=======================      -->
							
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

