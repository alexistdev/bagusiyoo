<x-back.admin-template>
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Master Tanaman</h1>
                </div><!-- /.col -->
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="{{route('admin.dashboard')}}">Home</a></li>
                        <li class="breadcrumb-item active">Master Tanaman</li>
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
                    <h3 class="card-title">Daftar Tanaman</h3>
                    <button class="btn btn-primary btn-sm float-right" data-toggle="modal" data-target="#modalTambah">
                        Tambah
                    </button>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-striped table-bordered" id="tableTanaman" style="width: 100%">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Nama Tanaman</th>
                                    <th scope="col">Action</th>
                                </tr>
                                </thead>
                                <tbody>

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

    <!-- Start : Modal Tambah -->
    <div class="modal fade" id="modalTambah" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Tambah Data</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="{{route('admin.savetanaman')}}" method="post">
                    @csrf
                    <div class="modal-body">

                        <div class="form-row">
                            <div class="col-md-12">
                                <label for="tambahNama"
                                       @if($errors->tambah->has('nama'))  class="text-danger labelForm" @endif>NAMA
                                    TANAMAN</label>
                                <input type="text" name="nama" id="tambahNama" maxlength="50"
                                       class="form-control @if($errors->tambah->has('nama'))   is-invalid @endif inputForm"
                                       value="{{old('nama')}}"/>
                                @if($errors->tambah->has('nama'))
                                    <span class="text-danger errorMessage">{{$errors->tambah->first('nama')}}</span>
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
    <!-- End : Modal Tambah -->


    <!-- Start : Modal Edit -->
    <div class="modal fade" id="modalEdit" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Edit Data</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="{{route('admin.updatetanaman')}}" method="post">
                    @csrf
                    @method('patch')
                    <div class="modal-body">
                        <div class="form-row">
                            <div class="col-md-12">
                                <input type="hidden" name="idTanaman" id="editIdTanaman">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-12">
                                <label for="tambahNama"
                                       @if($errors->edit->has('nama'))  class="text-danger labelForm" @endif>NAMA
                                    TANAMAN</label>
                                <input type="text" name="nama" id="editNama" maxlength="50"
                                       class="form-control @if($errors->edit->has('nama'))   is-invalid @endif inputForm"
                                       value="{{old('nama')}}"/>
                                @if($errors->edit->has('nama'))
                                    <span class="text-danger errorMessage">{{$errors->edit->first('nama')}}</span>
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
    <!-- End : Modal Edit -->

    <!-- Start : Modal Hapus -->
    <div class="modal fade" id="modalHapus" tabindex="-1" role="dialog"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <form action="{{route('admin.deletetanaman')}}" method="post">
                    @csrf
                    @method('delete')
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Hapus</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                @if($errors->hapus->has('idTanaman'))
                                    <span class="text-danger errorMessage">{{$errors->hapus->first('idTanaman')}}</span>
                                @endif
                                <input type="hidden" name="idTanaman" id="delIdTanaman">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                Apakah ingin menghapus data tanaman <span id="namaTanaman" class="text-danger font-weight-bold"></span> ini?
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
    <!-- End : Modal Hapus -->

    <x-back.js-layout/>
    <script>
        @if($errors->hasbag('tambah'))
        $('#modalTambah').modal({
            show: true,
        });
        @endif
        @if($errors->hasbag('edit'))
        $('#modalEdit').modal({
            show: true,
        });
        @endif
        @if($errors->hasbag('hapus'))
        $('#modalHapus').modal({
            show: true,
        });
        @endif
        $('.modal').on('hidden.bs.modal', function () {
            $('.labelForm').removeClass('text-danger');
            $('.inputForm').removeClass('is-invalid');
            $('.errorMessage').html('');
        })

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

        /** Saat tombol modal edit di click */
        $(document).on("click", ".open-editTanaman", function () {
            let fid = $(this).data('id');
            let fname = $(this).data('nama');
            $('#editIdTanaman').val(fid);
            $('#editNama').val(fname);
        });

        /** Saat tombol modal hapus di click */
        $(document).on("click", ".open-hapusTanaman", function () {
            let fid = $(this).data('id');
            let fname = $(this).data('nama');
            $('#delIdTanaman').val(fid);
            $('#namaTanaman').html(fname);
        });

        $('#tableTanaman').dataTable({
            pageLength: 10,
            responsive: true,
            processing: true,
            serverSide: true,
            ajax: "{{ route('admin.tanaman') }}",
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
                    }
                },
                {data: 'name', class: 'text-left'},
                {data: 'action', class: 'text-center', width: '25%'},
            ],
        });
    </script>
</x-back.admin-template>
