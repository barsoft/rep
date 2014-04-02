<?php ?>
<?php
require_once "../config.php";
require_once "../mysql-connect.php";
?>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="../resources/css/styles.css">
    <script src="http://vk.com/js/common.js"></script>
    <script src="openapi_vKontakte.js"></script>
    <script type="text/javascript" src="//vk.com/js/api/openapi.js?105"></script>
    <script type="text/javascript">
        VK.init({apiId: 4079792, onlyWidgets: true});
    </script>
    <script src="http://vk.com/js/api/openapi.js" type="text/javascript"></script>
    <body>
        <?php include("../header.php"); ?>
        <div>
            <script type="text/javascript">
                window.vkAsyncInit = function() {
                    VK.Auth.getLoginStatus(function(response) {
                        if (response.session) {
                            //        window.location = baseURL + '?op=main&page=auth';
                        } else {
                            VK.UI.button('vk_login');
                        }
                    });
                    VK.Observer.subscribe('auth.login', function(response) {
                        //    window.location = baseURL + '?op=main&page=auth';
                    });
                    VK.Observer.subscribe('auth.logout', function() {
                        //console.log('logout');
                    });
                    VK.Observer.subscribe('auth.statusChange', function(response) {
                        //console.log('statusChange');
                    });
                    VK.Observer.subscribe('auth.sessionChange', function(r) {
                        //console.log('sessionChange');
                    });

                    VK.init({
                        apiId: 4079792,
                        nameTransportPath: '/xd_receiver.html'
                    });
                    VK.UI.button('vk_login');
                };
                setTimeout(function() {
                    var el = document.createElement('script');
                    el.type = 'text/javascript';
                    el.src = '//vk.com/js/api/openapi.js';
                    el.async = true;
                    document.getElementById('vk_api_transport').appendChild(el);
                }, 0);
            </script>
            <!-- сами кнопки для входа и выхода -->
            <!-- вход -->
            <div id="Div1" onclick="doLogin();" style="width: 50px; height:20px; border-style:double">
                <div style="background: url(http://vk.com/images/btns.png) 0px -42px no-repeat; width: 21px; height: 21px" onmouseup="VK.UI._change(1, 0);" onmousedown="VK.UI._change(2, 0);" onmouseout="VK.UI._change(0, 0);" onmouseover="VK.UI._change(1, 0);">
                </div></div>

            <div id="Div2" onclick="doLogout();" style="width:50px; height:20px; border-style:double">
                <div style="background: url(http://vk.com/images/btns.png) 0px; width:50px; height:20px">
                </div>
            </div>
        </div>

        <div id="vk_groups"></div>
        <script type="text/javascript">
            VK.Widgets.Group("vk_groups", {mode: 0, width: "220", height: "400"}, 1);
        </script>

        <input type="button" onclick="GetFriends()" name="Friends" value="Показать список друзей" />

        <div id="out">sss</div>
        <?php include("../footer.php"); ?>
    </body>
</html>