 <% for(int i = 0 ; i < c.size() ; i++) { 
                            	   if(c.isEmpty()){
                           		%>
                           			<p> 등록된 앱이 없습니다. </p>
                           		<%
                            	   }
//  									Parameter Map = (Parameter)a.get(i);
 									HashMap tMap = (HashMap)c.get(i);
							    %>
                                <li>
                                    <!-- <i class="fa fa-camera bg-purple"></i> -->
                                    <div class="timeline-item">
                                        <span class="time"><a href="javascript:getImageData('<%=tMap.get("APP_UKID") %>')"><i class="fa fa-angle-down"></i></a></span>
                                        <h3 class="timeline-header"><%=tMap.get("ROWNUM") %> <%=tMap.get("APP_NAME") %> 앱</a></h3>
                                       	    <div class="timeline-body" id="test<%=tMap.get("APP_UKID") %>">
                                       	    	
                                        	</div>
                                    </div>
                                </li>
                              <%} %>




function getImageData(appUkid) {
				    $.ajax({    //ajax함수 안에 객체의 형태로 실행명령을 넣는다. 
				         url:'../api/app/getData',  //가져오고자하는 서버페이지 주소를 넣는다. url:'../api/app/getData'
				         type:'post',  //데이터를 가져온다는 뜻의 get 
				         cache : false, // 이걸 안쓰거나 true하면 수정해도 값반영이 잘안댐
				         data : { "APP_UKID" : appUkid }, // 서버에 json형태로 데이터 전송
	//			         data:JSON.stringify(data), 
				         /* .stringify Javascript 값을 json으로 파싱 */
				         success : function(data){  
				        	 /* url로 data를 보낸 후 success하면 데이터 수신 */
	 			        	/*  console.log("###################################");
	 		                 console.log(data.Icon_Info[0].ICON_INDEX);
	 		                 console.log(data.Icon_Info[1].ICON_INDEX);
 	 		                 console.log(data.Icon_Info[2].ICON_INDEX);
 	 		                 console.log(data.Icon_Info[3].ICON_INDEX);
 	 		                 console.log(data.Icon_Info[4].ICON_INDEX);
							 console.log(data);
	 		                 console.log("###################################"); */
			                 
			                 var temp = '';
			                 
			                 
			                 for(var i=0, len=data.Icon_Info.length; i<len;i++){
			                	//console.log("###################################");
			                	//console.log(data.Icon_Info[i]); 
	 		                 	temp += '<img alt="" src="getByteImage.do?imageIdx=' + data.Icon_Info[i].ICON_INDEX + '" class="margin">';
	 		                 	//temp += '<img alt="" src="getByteImage.do?imageIdx=' + data.Icon_Info[1].ICON_INDEX + '" class="margin">';
			                 } 
			                 
			                 
// 			                 temp += '<img alt="" src="getByteImage.do?imageIdx=' + data.Icon_Info[0].ICON_INDEX + '" class="margin">';
// 	 		                 temp += '<img alt="" src="getByteImage.do?imageIdx=' + data.Icon_Info[1].ICON_INDEX + '" class="margin">';
	 		                 	
							 temp += ' <div class="box-footer"> ';
							 temp += ' <button type="submit" value="modify" class="btn btn-primary" onclick="location.href=' + 'updateAppInfoForm.do' + '">수정</button> ';
						     temp += ' <button type="submit" value="delete" class="btn btn-primary">삭제</button> ';
							 temp += ' </div> ';
			                
							 $("#test" + appUkid).html(temp);
									         
				           }, 
				          error : function(request,status,error){ 
				          	console.log(request);
				          	console.log(status);
				          	console.log(error)
				          	alert("실패");
				          } 
				    });
			 }
		 

		 /* AJAX 활용 데이터 가져오기 js 클릭 이벤트 사용 */
		 


								<% for(int i = 0 ; i < c.size() ; i++) { 
                            	   if(c.isEmpty()){
                           		%>
                           			<p> 등록된 앱이 없습니다. </p>
                           		<%
                            	   }
//  									Parameter Map = (Parameter)a.get(i);
 									HashMap tMap = (HashMap)c.get(i);
							    %>
                                <li>
                                    <!-- <i class="fa fa-camera bg-purple"></i> -->
                                    <div class="timeline-item">
                                        <span class="time"><a href="#" class="rownum"><i class="fa fa-angle-down"></i></a></span>
                                        <input type="hidden" value="<%= tMap.get("APP_UKID")%>"/>
                                        <h3 class="timeline-header"><%=tMap.get("ROWNUM") %> <%=tMap.get("APP_NAME") %> 앱</a></h3>
                                       	<div class="timeline-body" id="content">
                                        </div>
                                    </div>
                                </li>
                              <%} %>


		 $(document).ready(function(){
			  $(".rownum").on('click',function
				 console.log($(this).parent().next('input').val());
			         alert("데이터 요청!");   //서버로 데이터 요청
			         //var data = {}
			         //data["APP_UKID"]=$('#APP_UKID').val();
			         // var a = $('#APP_UKID').val();
			         //alert("$$$"+a);
			    
			    $.ajax({    //ajax함수 안에 객체의 형태로 실행명령을 넣는다. 
			         url:'../api/app/getData',  //가져오고자하는 서버페이지 주소를 넣는다. url:'../api/app/getData'
			         type:'post',  //데이터를 가져온다는 뜻의 get 
			         cache : false, // 이걸 안쓰거나 true하면 수정해도 값반영이 잘안댐
			         data : { "APP_UKID" : $("#APP_UKID").val() }, // 서버에 json형태로 데이터 전송
// 			         data:JSON.stringify(data), 
			         /* .stringify Javascript 값을 json으로 파싱 */
			         success : function(data){  
			        	 /* url로 data를 보낸 후 success하면 데이터 수신 */
			        	 console.log("###################################");
		                 console.log(data.Icon_Info[0].ICON_INDEX);
		                 console.log(data.Icon_Info[1].ICON_INDEX);
		                 console.log(data.Icon_Info[2].ICON_INDEX);
		                 console.log(data.Icon_Info[3].ICON_INDEX);
		                 console.log(data.Icon_Info[4].ICON_INDEX);
		                 console.log("###################################");
		                 
		                 var temp = '';
		                 
		                 temp += '<img alt="" src="getByteImage.do?imageIdx=' + data.Icon_Info[0].ICON_INDEX + '" class="margin">';
		                 temp += '<img alt="" src="getByteImage.do?imageIdx=' + data.Icon_Info[1].ICON_INDEX + '" class="margin">';
		                 temp += '<img alt="" src="getByteImage.do?imageIdx=' + data.Icon_Info[2].ICON_INDEX + '" class="margin">';
		                 
						 temp += ' <div class="box-footer"> ';
						 temp += ' <button type="submit" value="modify" class="btn btn-primary" onclick="location.href=' + 'updateAppInfoForm.do' + '">수정</button> ';
					     temp += ' <button type="submit" value="delete" class="btn btn-primary">삭제</button> ';
						 temp += ' </div> ';
		                
		               	 $("#content").html(temp);
								         
			           }, 
			          error : function(request,status,error){ 
			          	console.log(request);
			          	console.log(status);
			          	console.log(error)
			          	alert("실패");
			          } 
			    });   
			   }); 	  
		}); 
