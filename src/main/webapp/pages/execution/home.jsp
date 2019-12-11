<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <section class="content">
        <h1>Hello Man Status1!!!</h1>
        <script>
            sessionStorage.setItem("accessToken", "<%=session.getAttribute("token")%>");
            function getAccessToken() {
                return sessionStorage.getItem("accessToken");
            }

        </script>

    </section>
</div>
