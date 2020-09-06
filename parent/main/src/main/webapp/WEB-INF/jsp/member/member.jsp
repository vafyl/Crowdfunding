<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH }/css/theme.css">
    <style>
        #footer {
            padding: 15px 0;
            background: #fff;
            border-top: 1px solid #ddd;
            text-align: center;
        }
        #topcontrol {
            color: #fff;
            z-index: 99;
            width: 30px;
            height: 30px;
            font-size: 20px;
            background: #222;
            position: relative;
            right: 14px !important;
            bottom: 11px !important;
            border-radius: 3px !important;
        }

        #topcontrol:after {
            /*top: -2px;*/
            left: 8.5px;
            content: "\f106";
            position: absolute;
            text-align: center;
            font-family: FontAwesome;
        }

        #topcontrol:hover {
            color: #fff;
            background: #18ba9b;
            -webkit-transition: all 0.3s ease-in-out;
            -moz-transition: all 0.3s ease-in-out;
            -o-transition: all 0.3s ease-in-out;
            transition: all 0.3s ease-in-out;
        }

    </style>
</head>
<body>
<div class="navbar-wrapper">
    <%@include file="/WEB-INF/jsp/common/member_top.jsp"%>
</div>
<div class="container">
    <div class="row clearfix">
        <div class="col-sm-3 col-md-3 column">
            <div class="row">
                <div class="col-md-12">
                    <div class="thumbnail" style="    border-radius: 0px;">
                        <img src="img/services-box1.jpg" class="img-thumbnail" alt="">
                        <div class="caption" style="text-align:center;">
                            <h3>
                                ${sessionScope.member.loginacct }
                            </h3>
                            <c:choose>
                                <c:when test="${member.authstatus eq '1'}">
                                    <span class="label label-warning" style="cursor:pointer;">实名认证申请中</span>
                                </c:when>
                                <c:when test="${member.authstatus eq '2'}">
                                    <span class="label label-success" style="cursor:pointer;">已实名认证</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="label label-danger" style="cursor:pointer;" onclick="window.location.href='${APP_PATH}/member/apply.htm'">未实名认证</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
            <div class="list-group">
                <div class="list-group-item active">
                    资产总览<span class="badge"><i class="glyphicon glyphicon-chevron-right"></i></span>
                </div>
                <div class="list-group-item " style="cursor:pointer;" onclick="window.location.href='${APP_PATH}/minecrowdfunding.htm'">
                    我的众筹<span class="badge"><i class="glyphicon glyphicon-chevron-right"></i></span>
                </div>
            </div>
        </div>
        <div class="col-sm-9 col-md-9 column">
            <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
                <b>
                    我的钱包
                </b>
            </blockquote>
            <div id="main" style="width: 600px;height:400px;"></div>
            <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
                <b>
                    理财
                </b>
            </blockquote>
            <div id="main1" style="width: 600px;height:400px;"></div>
            <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
                <b>
                    比例
                </b>
            </blockquote>
            <div id="main2" style="width: 600px;height:400px;"></div>
        </div>
    </div>
</div>
<div class="container" style="margin-top:20px;">
    <%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH }/script/docs.min.js"></script>
<script src="${APP_PATH }/script/back-to-top.js"></script>
<script src="${APP_PATH }/script/echarts.js"></script>
<script>
    $('#myTab a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })
    $('#myTab1 a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })

    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    option = {
        title: {
            text: '七日年化收益率(%)'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['基金','股票']
        },
        toolbox: {
            show: false,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']},
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis:  {
            type: 'category',
            boundaryGap: false,
            data: ['2019-05-16','2019-05-17','2019-05-18','2019-05-19','2019-05-20','2019-05-21','2019-05-22']
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} '
            }
        },
        series: [
            {
                name:'基金',
                type:'line',
                data:[1, 1, 5, 3, 2, 3, 2],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            },
            {
                name:'股票',
                type:'line',
                data:[1, -2, 2, 5, 3, 2, 4],
                markPoint: {
                    data: [
                        {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'},
                        [{
                            symbol: 'none',
                            x: '90%',
                            yAxis: 'max'
                        }, {
                            symbol: 'circle',
                            label: {
                                normal: {
                                    position: 'start',
                                    formatter: '最大值'
                                }
                            },
                            type: 'max',
                            name: '最高点'
                        }]
                    ]
                }
            }
        ]
    };
    myChart.setOption(option);
    var myChart1 = echarts.init(document.getElementById('main1'));

    // 指定图表的配置项和数据
    option1 = {
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['基金', '票据', '定期理财', '变现贷'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'直接访问',
                type:'bar',
                barWidth: '60%',
                data:[10, 52, 200, 334, 390, 330, 220]
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart1.setOption(option1);

    var myChart2 = echarts.init(document.getElementById('main2'));

    // 指定图表的配置项和数据
    option2 = {
        title : {
            text: '某站点用户访问来源',
            subtext: '纯属虚构',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
        },
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:335, name:'直接访问'},
                    {value:310, name:'邮件营销'},
                    {value:234, name:'联盟广告'},
                    {value:135, name:'视频广告'},
                    {value:1548, name:'搜索引擎'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart2.setOption(option2);

    function clearCookie() {
        var date=new Date();
        var cookieExpire=date.getTime()-1000; // 设置为一个过去的时间
        // 删除 cookie 时，名称、路径和域名必须相同
        document.cookie=" logincode=logincode  ; expire= " + cookieExpire + " ;path=/";

    }
</script>
</body>
</html>