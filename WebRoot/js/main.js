
var pic_count=0;     //当前显示图片的下标
var search_text="";  //搜索框中的值(实时改变)
var map, geolocation;  //地图·定位插件
var positionX=0,positionY=0;	//自动定位后的经纬度坐标
var address="";     //地址信息
var auto_pic=5000;  //自动更换首页图片时间
var distance=0;		//距离

$(document).ready(function(){
	init();
	map();

	$("#content_main").mouseover(function(){
		$("#btn_left").show();
		$("#btn_right").show();
	});
	$("#content_main").mouseout(function(){
		$("#btn_left").hide();
		$("#btn_right").hide();
	});
	$("#map_close").click(function(){
		$("#map_container").css("display","none");
	});
	

	$("#search").bind('input propertychange', function(){
		search_text=$("#search").val();
	}); 
	setInterval(function(){			//设置一个定时器自动更换图片
		pic_count=(++pic_count)%$("#pic_box img").length;
		$("#pic_box img").each(function(index){
			if(index==pic_count){
				$(this).fadeIn();
			}else{
				$(this).fadeOut();
			}			
		});
	},auto_pic);
});

function init(){
	$("#pic_box img").each(function(index){
			if(0===index){
				$(this).show();
			}else{
				$(this).hide();
			}			
		});
	var footer_nav_width=$("#footer_nav ul").width();
	var footer_nav_height=$("#footer_nav ul").height();
	$("#footer_nav ul").css("margin-left",String(-footer_nav_width*0.5)+"px");
	$("#footer_nav ul").css("margin-top",String(-footer_nav_height*0.5)+"px");
	
}

function turn_left(){
		pic_count=(--pic_count)%$("#pic_box img").length;
		if(pic_count<0){
			pic_count=$("#pic_box img").length-1;
		}
		$("#pic_box img").each(function(index){
			if(index==pic_count){
				$(this).fadeIn();
			}else{
				$(this).fadeOut();
			}			
		});
}
function turn_right(){
		pic_count=(++pic_count)%$("#pic_box img").length;
		$("#pic_box img").each(function(index){
			if(index==pic_count){
				$(this).fadeIn();
			}
			else{
				$(this).fadeOut();
			}	
		});
}

function map(){
	map = new AMap.Map('container',{
        zoom: 15,
        //center:[126.559615,45.866581],//哈师大江北校区的地理坐标
        resizeEnable: true
    });

    AMap.plugin(['AMap.Autocomplete','AMap.PlaceSearch'],function(){
      var autoOptions = {
      	city:"哈尔滨",
        input: "search",
        extensions: "all"
      };
      autocomplete= new AMap.Autocomplete(autoOptions);
      var placeSearch = new AMap.PlaceSearch({
            map:map,
            extensions:"all"
      });
      AMap.event.addListener(autocomplete, "select", function(e){
         //TODO 针对选中的poi实现自己的功能
         placeSearch.search(e.poi.name);
      });
    });

    /*map.plugin('AMap.Geolocation', function() {
        geolocation = new AMap.Geolocation({
            enableHighAccuracy: true,//是否使用高精度定位，默认:true
            timeout: 20000,          //超过20秒后停止定位，默认：无穷大
            buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
            zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
            buttonPosition:'RB'
        });
        map.addControl(geolocation);
        geolocation.getCurrentPosition();
        AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
        AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
    });





    //解析定位结果
    function onComplete(data) {
    	positionX=data.position.getLng();
    	positionY=data.position.getLat();
    	$("body").css("display","block");
    }
    //解析定位错误信息
    function onError(data) {
        alert("定位失败");
    }*/

}

function distance_fun(){
	 //步行导航
	  map.plugin(["AMap.Walking"],function(){
	    var walking = new AMap.Walking({
	        map: map,
	        panel: "panel"
	    }); 
	    //根据起终点坐标规划步行路线
	    walking.search([positionX,positionY],[126.559382,45.866519],
                   function(status,result){
	                           if(status==='complete'){
	                               var s=result.routes;
	                               distance=s[0].distance;
		                               	if(address.indexOf("哈师大江北")!= -1||address.indexOf("哈尔滨师范大学江北")!= -1||address.indexOf("哈尔滨师范大学松北")!= -1||address.indexOf("哈师大松北")!= -1){
		                               		    alert("可以配送");
		                               	$.ajax({
                                                url:"UserServlet",
                                                 type:"post",
                                                 data:{flag:"address_now",search_text:address},
                                                 success:function(data){
                                                    window.location.href='UserInfo/Main.jsp';
			                                      }
                                               });
		                               	}else{
											if(distance<2000){
												 alert("距离 "+distance+" 米,可以配送");
												$.ajax({
                                                url:"UserServlet",
                                                 type:"post",
                                                 data:{flag:"address_now",search_text:address},
                                                 success:function(data){
                                                    window.location.href='UserInfo/Main.jsp';
			                                      }
                                               });
											}else{
												alert("距离 "+distance+" 米,不与配送");
											}
		                               	}
		                               
	                            }
	    					}
    		);
	  });
	  
}

   
   function distance_fun_add(){
	 //步行导航
	  map.plugin(["AMap.Walking"],function(){
	    var walking = new AMap.Walking({
	        map: map,
	        panel: "panel"
	    }); 
	    //根据起终点坐标规划步行路线
	    walking.search([positionX,positionY],[126.559382,45.866519],
                   function(status,result){
	                           if(status==='complete'){
	                               var s=result.routes;
	                               distance=s[0].distance;
		                               	if(address.indexOf("哈师大江北")!= -1||address.indexOf("哈尔滨师范大学江北")!= -1||address.indexOf("哈尔滨师范大学松北")!= -1||address.indexOf("哈师大松北")!= -1){
		                               		    alert("可以配送");
		                              
		                               	}else{
											if(distance<2000){
												 alert("距离 "+distance+" 米,可以配送");
												
											}else{
												alert("距离 "+distance+" 米,不与配送");
												$("#addressName").val("");
											}
		                               	}
		                               
	                            }
	    					}
    		);
	  });
	  
}
   
    function distance_fun_up(){
	 //步行导航
	  map.plugin(["AMap.Walking"],function(){
	    var walking = new AMap.Walking({
	        map: map,
	        panel: "panel"
	    }); 
	    //根据起终点坐标规划步行路线
	    walking.search([positionX,positionY],[126.559382,45.866519],
                   function(status,result){
	                           if(status==='complete'){
	                               var s=result.routes;
	                               distance=s[0].distance;
		                               	if(address.indexOf("哈师大江北")!= -1||address.indexOf("哈尔滨师范大学江北")!= -1||address.indexOf("哈尔滨师范大学松北")!= -1||address.indexOf("哈师大松北")!= -1){
		                               		    alert("可以配送");
		                              
		                               	}else{
											if(distance<2000){
												 alert("距离 "+distance+" 米,可以配送");
												
											}else{
												alert("距离 "+distance+" 米,不与配送");
												$("#addressName_u").val("");
											}
		                               	}
		                               
	                            }
	    					}
    		);
	  });
	  
}



	 function geocoder() {
		 	if(address!=null){
		 		var geocoder = new AMap.Geocoder({
		            city: "哈尔滨", //城市，默认：“全国”
		            radius: 2000 //范围，默认：500
		        });
		        //地理编码,返回地理编码结果
		        geocoder.getLocation(address, function(status, result) {
		            if (status === 'complete' && result.info === 'OK') {
		                geocoder_CallBack(result);
		                distance_fun();  
		            }
		        });
		 	}  
		 	 
	    }
	 
	  function geocoder_add() {
		 	if(address!=null){
		 		var geocoder = new AMap.Geocoder({
		            city: "哈尔滨", //城市，默认：“全国”
		            radius: 2000 //范围，默认：500
		        });
		        //地理编码,返回地理编码结果
		        geocoder.getLocation(address, function(status, result) {
		            if (status === 'complete' && result.info === 'OK') {
		                geocoder_CallBack(result);
		                distance_fun_add();  
		            }
		        });
		 	}  
		 	 
	    }
	  
	  function geocoder_up() {
		 	if(address!=null){
		 		var geocoder = new AMap.Geocoder({
		            city: "哈尔滨", //城市，默认：“全国”
		            radius: 2000 //范围，默认：500
		        });
		        //地理编码,返回地理编码结果
		        geocoder.getLocation(address, function(status, result) {
		            if (status === 'complete' && result.info === 'OK') {
		                geocoder_CallBack(result);
		                distance_fun_up();  
		            }
		        });
		 	}  
		 	 
	    }

    //地理编码返回结果展示
    function geocoder_CallBack(data) {
        //地理编码结果数组
        var geocode = data.geocodes;
        for (var i = 0; i < geocode.length; i++) {
             positionX=geocode[i].location.getLng();
          	 positionY=geocode[i].location.getLat();
        }
    }




function search_request(){
	
	if($("#search").val()!=""){
		$("#map_container").css("display","block");
		address="哈尔滨市"+$("#search").val();
		geocoder();
	}
	else{
		alert("请输入地址!");
	}
	
}

function address_request()
{
	if($("#addressName").val()!=""){
		address="哈尔滨市"+$("#addressName").val();
		geocoder_add();
	}
}

function address_request2()
{
	if($("#addressName_u").val()!=""){
		address="哈尔滨市"+$("#addressName_u").val();
		geocoder_up();
	}
}


function login_request(){
	alert("登录");
	// $.post('',{user:$("#user").val(),password:$("#password").val(),code:$("#auth").val()},
	// 	 	function(data){
	// 	 			alert(data);
	//				window.location.href='';
	// });
}
function reloadCode(){		//刷新验证码
	var time=new Date();
	var img=document.getElementById("code").src="<%=request.getContextPath()%>/servlet/ImageServlet?d="+time;
}
