<html>
    <head>
        <title>Esper Market Data Ticker CEP Sample</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type="text/javascript"
                charset="utf-8"></script>

        <style type="text/css" media="screen">
            body {
                background: #d3d3d3;
                color: #000;
                font-size: .9em;
            }
            td.head {
                font-weight: bold;
                font-style: italic;
            }
            td.higher {
                color: green;
            }
            td.lower {
                color: red;
            }
            table.ticker_data{

            }

        </style>

        <script type="text/javascript" charset="utf-8">
            function renderMarketData(type, data) {
                var html = "<table class='market_daya' border='1'>"
                $.each(data, function(key, val) {
                    html = html + "<tr>" + "<td class='head'>" + key + "</td>"
                    html = html + renderCell(key, "avg(price)", val.price);
                    html = html + renderCell(key, "avg(bid)", val.bid);
                    html = html + renderCell(key, "avg(ask)", val.ask);

                    html = html + "</tr>"
                });
                html = html + "</table>";
                $("#market_data").html(
                        html
                );
            }

            function renderCell(symbol, propertyName, propertyValue) {
                var rowHtml = "";
                var cellId = (symbol + propertyName).replace("=", "").replace("(", "").replace(")", "");
                var curVal = $("#" + cellId).text();
                var cellClass = "";
                if (curVal && curVal < propertyValue) {//if new value is higher, render in green
                    cellClass = cellClass + "higher";
                }
                if (curVal && curVal > propertyValue) {//if new value is higher, render in red
                    cellClass = cellClass + "lower";
                }
                rowHtml = rowHtml + "<td id='" + cellId + "' class='" + cellClass + "'>" + propertyValue + "</td>"
                return rowHtml;
            }

            function pollMarketData() {
                /* This requests the url "msgsrv.php"
            When it complete (or errors)*/
                $.ajax({
                    type: "GET",
                    url: "inplace.json",
                    dataType:'json',
                    async: true, /* If set to non-async, browser shows page as "Loading.."*/
                    cache: false,
                    timeout:50000, /* Timeout in ms */

                    success: function(data) {
                        renderMarketData("success", data);/* Add response to a market_data div (with the "new" class)*/
                        setTimeout(
                                'pollMarketData()', /* Request next message */
                                1000 /* ..after 1 seconds */
                        );
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        renderMarketData("error", textStatus + " (" + errorThrown + ")");
                        setTimeout(
                                'pollMarketData()', /* Try again after.. */
                                "15000");/* milliseconds (15seconds) */
                    },
                });
            };
            $(document).ready(function() {
                pollMarketData();/* Start the inital request */
            });
        </script>
    </head>
    <body>
        <h1> Esper Stock Ticker Sample</h1>
        <p class="info">The sample app is randomly generating event stream at the rate of 10000 events per second. </p>
        <p class="info">Table below displays average stock price, bid and amounts over last 30 seconds, as processed by the Esper statement below.</p>
        <p class="info">Esper statement: <i>select symbol,avg(ask),avg(bid),avg(price) from com.opencredo.sandbox.aleksav.esper.domain.MarketDataEvent.win:time(30 second) group by symbol</i></p>
        <p class="info">Download the sample code from <a href="http://www.github.com/opencredo/esper-stockticker-sample">OpenCredo public repository on github</a>.</p>
        <p class="info">Visit <a href="http://www.opencredo.com/blog">OpenCredo blog</a> to read more about Esper and other technology topics.</p>

        <div id="market_data">
            No Data
        </div>
    </body>
</html>