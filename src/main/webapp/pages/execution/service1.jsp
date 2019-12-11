<%@ page contentType="text/html; charset=UTF-8" %>

<div class="main-body">
    <div class="col-sm-12">
        <div class="box-body">
            <div>
                <table id="tbl1" class="table table-striped table-bordered" style="width:100%;">
                    <thead class="tbl-thead">
                    <tr>
                        <th class="tblheader lb-txt-center" style="width: 12%;"
                            rowspan="1">ลำดับ</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function initTbl(data) {
        data = data || [];
        $("#tbl1").DataTable({
            destroy: true,
            searching: false,
            bLengthChange: false,
            paging: true,
            bInfo: false,
            sInfo: false,
            bProcessing: true,
            bSort: false,
            autoWidth: false,
            "oLanguage": {
                "sEmptyTable": "--ไม่พบข้อมูล--"
            },
            columnDefs: [
                {className: "text-center", "targets": [0]}
            ],
            data: data,
            columns: [
                {
                    data: null,
                    defaultContent: ""
                }
            ]
        });
    }

    function callService1() {
        var url = "http://localhost:8765/api/job/app/info";
        $.ajax({
            url: url,
            type: 'GET',
            success: function (data) {
                console.log(data);
            }
        });
    }

    function callService2() {
        var url = "http://localhost:8765/api/job/app/message/asdf";
        $.ajax({
            url: url,
            type: 'GET',
            // data: "asdf",
            success: function (data) {
                console.log(data);
            }
        });
    }

    function callService3() {
        var url = "http://localhost:8765/api/job/app/message";
        $.ajax({
            url: url,
            type: 'GET',
            data: "name=PCC Co, Ltd",
            success: function (data) {
                console.log(data);
            }
        });
    }

    function callService4() {
        var params = {companyName: "PCC CO,Ltd", address: "คลองเตย"};
        var url = "http://localhost:8765/api/job/app/job-detail";
        $.ajax({
            url: url,
            type: 'POST',
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(params),
            success: function (data) {
                console.log(data);
            }
        });
    }

    // callService2();
    // callService3();
    callService4();
</script>
