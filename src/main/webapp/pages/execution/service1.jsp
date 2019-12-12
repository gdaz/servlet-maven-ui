<%@ page contentType="text/html; charset=UTF-8" %>

<div class="main-body">
    <div class="box-body">
        <div class="row">
            <div class="project-form">
                <div class="col-sm-12">
                    <div class="row">
                        <label for="projectName">Project Name</label>
                        <input type="text" class="form-control" id="projectName">
                    </div>
                    <div class="row">
                        <label for="projectDesc">Project Description</label>
                        <input type="text" class="form-control" id="projectDesc">
                    </div>
                    <div class="row">
                        <input type="text" id="slider-input" class="slider" value="100">
                    </div>
                    <div class="row">
                        <button class="btn btn-info" id="save" onclick="handleSelectProject(this)">Save</button>
                        <button class="btn btn-info" id="cancel" onclick="cancelProject(this)">Cancel</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div id="pjson"></div>
            <div class="project-form">
                <table id="tbl1" class="table table-striped table-bordered table-hover" style="width:100%;">
                    <thead class="tbl-thead">
                    <tr>
                        <th class="tblheader lb-txt-center" style="width: 6%;"
                            rowspan="1">ลำดับ
                        </th>
                        <th class="tblheader lb-txt-center" style="width: 15%;"
                            rowspan="1">ชื่อโปรเจค
                        </th>
                        <th class="tblheader lb-txt-center" style="width: 20%;"
                            rowspan="1">คำอธิบาย
                        </th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    var myModule = (function () {
        var projectId = null;
        var mySlider = new Slider("#slider-input", {
            min: 0,
            max: 100,
            value: 0,
            step: 1
        });

        return {
            setValue: function (percentage) {
                mySlider.setValue(percentage);
            },
            setId: function (id) {
                projectId = id;
            },
            getId: function () {
                return projectId;
            }
        }
    })();

    $(document).ready(function () {
        initTbl([]);
    });

    function initForm() {
        $("#projectName").val("");
        $("#projectDesc").val("");
        myModule.setValue(0);
        myModule.setId(null);
    }

    function initTbl(data) {
        data = data || [];
        var table = $("#tbl1").DataTable({
            destroy: true,
            ordering: false,
            searching: false,
            lengthChange: false,
            info: true,
            paging: true,
            processing: true,
            serverSide: true,
            autoWidth: false,
            pageLength: 5,
            deferRender: true,
            oLanguage: {
                emptyTable: "--ไม่พบข้อมูล--"
            },
            "drawCallback": function (settings) {
                $('#tbl1').off('click', 'tbody tr');
                $('#tbl1').on('click', 'tbody tr', function (e) {
                    e.stopImmediatePropagation();
                    var data = table.row($(this).closest('tr')).data();
                    $("#projectName").val(data.projectName);
                    $("#projectDesc").val(data.projectDescription);
                    myModule.setValue(data.projectPercentage);
                    myModule.setId(data.projectId);
                });
            },
            columnDefs: [
                {className: "text-center", "targets": [0, 1, 2]},
                {
                    "searchable": false,
                    "orderable": false,
                    "targets": 0
                }
            ],
            data: data,
            order: [[1, 'asc']],
            columns: [
                {
                    data: 'seq',
                    sortable: false,
                    render: function (data, type, row, meta) {
                        row.seq = meta.row + meta.settings._iDisplayStart + 1;
                        return meta.row + meta.settings._iDisplayStart + 1;
                    }
                },
                {
                    data: "projectName",
                    defaultContent: ""
                },
                {
                    data: "projectDescription",
                    defaultContent: ""
                }
            ],
            ajax: {
                url: "http://localhost:8765/api/job/project/project-list-pagable",
                type: "POST",
                data: function (d) {
                    if (table === undefined) {
                        d.offset = 0
                        // return "offset=" + 0;
                    } else {
                        var info = table.page.info();
                        d.offset = info.page;
                        // return "offset=" + info.page;
                    }
                },
                dataFilter: function (data) {
                    var json = JSON.parse(data);
                    json.recordsTotal = json.totalElements;
                    json.recordsFiltered = json.totalElements;
                    json.data = json.content;
                    json.page = json.pageable.pageNumber;

                    console.log(JSON.stringify(data));

                    var info = table.page.info();
                    console.log(info);

                    return JSON.stringify(json); // return JSON string
                }
            }
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

    function handleSelectProject(e) {
        if (!myModule.getId()) {
            saveProjectData();
        } else {
            updateProjectData();
        }

        var table = $('#tbl1').DataTable();
        table.ajax.reload();
    }

    function updateProjectData() {
        var data = {
            projectName: $("#projectName").val(),
            projectDescription: $("#projectDesc").val(),
            projectPercentage: $("#slider-input").val(),
            projectId: myModule.getId()
        };

        var url = "http://localhost:8765/api/job/project/current-project";
        $.ajax({
            url: url,
            type: 'PUT',
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
            success: function (data) {
                console.log(data);
                initForm();
                projectList();
            }
        });

    }

    function saveProjectData() {
        var data = {
            projectName: $("#projectName").val(),
            projectDescription: $("#projectDesc").val(),
            projectPercentage: $("#slider-input").val()
        };

        var url = "http://localhost:8765/api/job/project/new-project";
        $.ajax({
            url: url,
            type: 'POST',
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
            success: function (data) {
                console.log(data);
                initForm();
                projectList();
            }
        });

    }

    function projectList() {
        var url = "http://localhost:8765/api/job/project/project-list";
        $.ajax({
            url: url,
            type: 'GET',
            success: function (data) {
                initTbl(data.result);
            }
        });

    }

    function cancelProject() {
        initForm();
    }

    // projectList();
</script>
