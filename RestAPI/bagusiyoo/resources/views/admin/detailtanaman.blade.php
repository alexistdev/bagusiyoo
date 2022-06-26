<x-back.admin-template>
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Detail Aktivitas Tanaman</h1>
                </div><!-- /.col -->
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="{{route('admin.dashboard')}}">Home</a></li>
                        <li class="breadcrumb-item active"><a href="{{route('admin.tanaman')}}">Master Tanaman</a></li>
                        <li class="breadcrumb-item active">Detail</li>
                    </ol>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <div class="card card-dark">
                <div class="card-header">
                    <h3 class="card-title">Daftar Aktivitas Tanaman</h3>

                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-striped table-bordered" id="tableWaktu" style="width: 100%">
                                <thead>
                                <tr>
                                    <th scope="col" class="text-center">Hari ke</th>
                                    <th scope="col" class="text-center">Aktivitas</th>
                                    <th scope="col" class="text-center">Jadwal</th>
                                    <th scope="col" class="text-center">Action</th>
                                </tr>
                                </thead>
                                <tbody>

                                @foreach($waktuTanam as $row)
                                    <tr>
                                        <td>{{$row->hari_ke}}</td>
                                        <td>
                                            @foreach($row->aktif as $act)
                                                {{$act->name}} ,
                                            @endforeach
                                        </td>
                                        <td>{{$row->tanggal}}</td>
                                        <td>
                                            <button class="btn btn-success btn-sm modal-detail-edit" data-id="{{$row->id}}" data-idtanaman="{{$idTanaman}}" data-toggle="modal" data-target="#modalDetail">Edit</button>
                                            <button class="btn btn-primary btn-sm modal-detail-tambah" data-id="{{$row->id}}" data-idtanaman="{{$idTanaman}}" data-toggle="modal" data-target="#modalTambah">Tambah</button>

                                        </td>
                                    </tr>
                                @endforeach
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
                <!-- /.card-body -->
                <div class="card-footer">
                    Footer
                </div>
                <!-- /.card-footer-->
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
    <!-- Modal Detail Aktivitas -->
    <div class="modal fade" id="modalDetail" tabindex="-1"  aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Daftar Aktivitas Tanaman</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="modal-wrapper">
                        <table class="table table-bordered table-striped mb-0 display" id="tbDetail"
                               style="width: 100%">
                            <thead>
                            <tr>
                                <th scope="col" class="text-center">#</th>
                                <th scope="col" class="text-center">NAMA AKTIVITAS</th>
                                <th scope="col" class="text-center">ACTION</th>
                            </tr>
                            </thead>

                            <tbody>
                            </tbody>
                        </table>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Closed</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Start: Modal Tambah Aktivitas -->
    <div class="modal fade" id="modalTambah" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Tambah Data Aktivitas </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form id="tambahForm" action="" method="post">
                    @csrf
                    <div class="modal-body">

                        <div class="form-row">
                            <div class="col-md-12">
                                <input type="hidden" name="idtanaman" id="idtanaman">
                                <label for="tambahNama"
                                       @if($errors->tambahAktivitas->has('nama'))  class="text-danger labelForm" @endif>NAMA
                                    AKTIVTAS</label>
                                <input type="text" name="nama" id="tambahNama" maxlength="50"
                                       class="form-control @if($errors->tambahAktivitas->has('nama'))   is-invalid @endif inputForm"
                                       value="{{old('nama')}}"/>
                                @if($errors->tambahAktivitas->has('nama'))
                                    <span class="text-danger errorMessage">{{$errors->tambahAktivitas->first('nama')}}</span>
                                @endif
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- End: Modal Tambah Aktivitas -->

    <!-- Start: Modal Edit Aktivitas -->
    <div class="modal fade" id="modalEditAktivitas" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Edit Data Aktivitas </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form id="editForm" action="" method="post">
                    @csrf
                    @method('patch')
                    <div class="modal-body">
                        <div class="form-row">
                            <div class="col-md-12">
                                <input type="hidden" name="idtanaman" id="idEditTanaman">
                                <input type="hidden" name="idaktivitas" id="idEditAktivitas">
                                <label for="editNama"
                                       @if($errors->editAktivitas->has('nama'))  class="text-danger labelForm" @endif>NAMA
                                    AKTIVTAS</label>
                                <input type="text" name="nama" id="editNama" maxlength="50"
                                       class="form-control @if($errors->editAktivitas->has('nama'))   is-invalid @endif inputForm"
                                       value="{{old('nama')}}" required/>
                                @if($errors->editAktivitas->has('nama'))
                                    <span class="text-danger errorMessage">{{$errors->editAktivitas->first('nama')}}</span>
                                @endif
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- End: Modal Edit Aktivitas -->

    <!-- Start: Modal Hapus Aktivitas -->
    <div class="modal fade" id="modalHapusAktivitas" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Edit Data Aktivitas </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form id="hapusForm" action="" method="post">
                    @csrf
                    @method('delete')
                    <div class="modal-body">
                        <div class="form-row">
                            <div class="col-md-12">
                                <input type="hidden" name="idtanaman" id="idHapusTanaman">
                                <input type="hidden" name="idaktivitas" id="idHapusAktivitas">
                                Apakah anda mau menghapus data aktivitas ini?
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-danger">Hapus</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- End: Modal Hapus Aktivitas -->

    <x-back.js-layout/>
    <script>
        @if($errors->hasbag('tambahAktivitas'))
            $('#modalTambah').modal({
                show: true
            });
        @endif
        @if($errors->hasbag('editAktivitas'))
        $('#modalEditAktivitas').modal({
            show: true
        });
        @endif
        @if ($message = Session::get('success'))
        $(document).Toasts('create', {
            class: 'bg-success',
            title: 'Berhasil',
            body: '{{ $message }}.'
        });
        @endif
        @if ($message = Session::get('error'))
        $(document).Toasts('create', {
            class: 'bg-danger',
            title: 'Gagal',
            body: '{{ $message }}'
        });
        @endif
        @if ($message = Session::get('hapus'))
        $(document).Toasts('create', {
            class: 'bg-danger',
            title: 'Hapus',
            body: '{{ $message }}'
        });
        @endif

        $('#tableWaktu').dataTable({
            responsive: true,
            columns:[
                {'width': '8%','class': 'text-center'},
                null,
                {'width': '10%','class': 'text-center'},
                {'width': '20%','class': 'text-center'},
            ]
        });
        $(document).on("click", ".open-hapusAktivitas", function (e) {
            e.preventDefault();
            let fid = $(this).data('id');
            let ftanaman = $(this).data('tanaman');
            let fhari = $(this).data('hari');
            let base_url = "{{route('admin.deleteaktivitas',":id")}}";
            let replaceURL = base_url.replace(":id",fhari);
            $('#hapusForm').attr('action', replaceURL);
            $('#idHapusTanaman').val(ftanaman);
            $('#idHapusAktivitas').val(fid);
        });

        $(document).on("click", ".open-editAktivitas", function (e) {
            e.preventDefault();
            let fid = $(this).data('id');
            let fnama = $(this).data('nama');
            let fhari = $(this).data('hari');
            let ftanaman = $(this).data('tanaman');
            $('#editNama').val(fnama);
            let base_url = "{{route('admin.editaktivitas',":id")}}";
            let replaceURL = base_url.replace(":id",fhari);
            $('#editForm').attr('action', replaceURL);
            $('#idEditTanaman').val(ftanaman);
            $('#idEditAktivitas').val(fid);

        });

        $(document).on("click", ".modal-detail-tambah", function (e) {
            e.preventDefault();
            let fid = $(this).data('id');
            let fidtanaman = $(this).data('idtanaman');
            let base_url = "{{route('admin.tambahaktivitas',":id")}}";
            let replaceURL = base_url.replace(":id",fid);
            $('#tambahForm').attr('action', replaceURL);
            $('#idtanaman').val(fidtanaman);
        });

        $(document).on("click", ".modal-detail-edit", function () {
            let fid = $(this).data('id');
            let base_url = "{{route('admin.aktivitastanaman')}}";
            $(function () {


                $('#tbDetail').DataTable({
                    responsive: true,
                    processing: true,
                    serverSide: true,
                    ajax: {
                        type: 'GET',
                        url: base_url,
                        async:true,
                        data: {
                            'idTanam': fid,
                        }
                    },
                    columns: [
                        {
                            data: 'index',
                            class: 'text-center',
                            defaultContent: '',
                            orderable: false,
                            searchable: false,
                            width: '5%',
                            render: function (data, type, row, meta) {
                                return meta.row + meta.settings._iDisplayStart + 1; //auto increment
                            },

                        },
                        {data: 'name', class: 'text-left'},
                        {data: 'action', class: 'text-left'},


                    ],
                    "bDestroy": true
                });
            });
            $('#tablePending').DataTable().clear();

        });
    </script>
</x-back.admin-template>
