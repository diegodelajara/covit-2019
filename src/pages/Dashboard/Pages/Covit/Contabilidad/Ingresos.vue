<template>
    <div class="row">
        <div class="header text-center">
            <!--h3 class="title">Ingresos</h3-->
        </div>
        <div class="col-12">
            <card card-body-classes="table-full-width" no-footer-line>
                <h4 slot="header" class="card-title">Ingresos</h4>
                <div>
                  <div class="content">
                    <div class="col-4 d-flex justify-content-center justify-content-sm-between flex-wrap">
                        <n-button class="btn-round btn-primary" type="info" @click.native="modals.classic = true">
                        <!--i slot="label" class="now-ui-icons travel_info"></i-->
                        Nuevo Ingreso
                        </n-button>
                    </div>
                    <div class="col-4 d-flex justify-content-center justify-content-sm-between flex-wrap">
                        <!--b-button type="button" @click="largeModal = true">Nuevo</b-button-->
                        <!-- <el-select
                        class="select-primary mb-3"
                        style="width: 70px"
                        v-model="pagination.perPage"
                        placeholder="Per page">
                          <el-option
                              class="select-default"
                              v-for="item in pagination.perPageOptions"
                              :key="item"
                              :label="item"
                              :value="item">
                          </el-option>
                        </el-select> -->

                    </div>
                    <div class="col-4 d-flex justify-content-center justify-content-sm-between flex-wrap">

                        <fg-input>
                          <el-input type="search"
                                      class="mb-3"
                                      clearable
                                      prefix-icon="el-icon-search"
                                      style="width: 200px"
                                      placeholder="Search records"
                                      v-model="searchQuery"
                                      aria-controls="datatables">
                          </el-input>
                        </fg-input>
                    </div>
                  </div>
                <el-table stripe
                            style="width: 100%;"
                            :data="queriedData">
                    <el-table-column v-for="column in tableColumns"
                                    :key="column.label"
                                    :min-width="column.minWidth"
                                    :prop="column.prop"
                                    :label="column.label">
                    </el-table-column>
                    <el-table-column
                    :min-width="135"
                    fixed="right"
                    label="Actions">
                    <div slot-scope="props" class="table-actions">
                        <n-button @click.native="handleLike(props.$index, props.row)"
                                class="like"
                                type="info"
                                size="sm" round icon>
                        <i class="fa fa-heart"></i>
                        </n-button>
                        <n-button @click.native="handleEdit(props.$index, props.row)"
                                class="edit"
                                type="warning"
                                size="sm" round icon>
                        <i class="fa fa-calendar"></i>
                        </n-button>
                        <n-button @click.native="handleDelete(props.$index, props.row)"
                                class="remove"
                                type="danger"
                                size="sm" round icon>
                        <i class="fa fa-times"></i>
                        </n-button>
                    </div>
                    </el-table-column>
                </el-table>
                </div>
                <div slot="footer" class="col-12 d-flex justify-content-center justify-content-sm-between flex-wrap">
                <div class="">
                    <p class="card-category">Showing {{from + 1}} to {{to}} of {{total}} entries</p>
                </div>
                <n-pagination class="pagination-no-border"
                                v-model="pagination.currentPage"
                                :per-page="pagination.perPage"
                                :total="total">
                </n-pagination>
                </div>
            </card>
        </div>
        <!-- Modal -->
        <modal :show.sync="modals.classic">
          <h4 slot="header" class="title title-up">Nuevo Ingreso</h4>
          <Form :form-data="formData"/>
        </modal>
        <!-- Fin Modal -->
    </div>
</template>

<script>
  import {Table, TableColumn, Select, Option} from 'element-ui'
  import {Pagination as NPagination} from 'src/components'
  import users from './contabilidad'
  import Fuse from 'fuse.js'
  import swal from 'sweetalert2'
  import Modal from 'src/components/Modal'
  import Form from 'src/components/Covit/Form'
  import { ingresosRef } from 'src/firebase'

  export default {
    firebase: {
      ingresosRef
    },
    data () {
      return {
        formData: {
          nombre: null,
          monto: null
        },
        modals: {
          classic: false
        },
        pagination: {
          perPage: 5,
          currentPage: 1,
          perPageOptions: [5, 10, 25, 50],
          total: 0
        },
        searchQuery: '',
        propsToSearch: ['nombre'],
        tableColumns: [
          {
            prop: 'nombre',
            label: 'Nombre',
            minWidth: 200
          },
          {
            prop: 'monto',
            label: 'Monto',
            minWidth: 250
          }
        ],
        tableData: users,
        //tableData: ingresosRef,
        searchedData: [],
        fuseSearch: null
      }
    },
    components: {
      NPagination,
      [Select.name]: Select,
      [Option.name]: Option,
      [Table.name]: Table,
      [TableColumn.name]: TableColumn,
      Modal,
      Form
    },
    computed: {
      queriedData () {
        //let result = this.tableData;
        let result = this.ingresosRef;
        if(this.searchedData.length > 0){
          result = this.searchedData;
        }
        return result.slice(this.from, this.to)
      },
      to () {
        let highBound = this.from + this.pagination.perPage
        if (this.total < highBound) {
          highBound = this.total
        }
        return highBound
      },
      from () {
        return this.pagination.perPage * (this.pagination.currentPage - 1)
      },
      total () {
        return this.searchedData.length > 0 ? this.searchedData.length : this.tableData.length;
      }
    },
    methods: {
      agregarIngreso() {
        console.log(this.formData)
      },
      handleLike (index, row) {
        swal({
          title: `You liked ${row.name}`,
          buttonsStyling: false,
          type: 'success',
          confirmButtonClass: 'btn btn-success btn-fill'
        })
      },
      handleEdit (index, row) {
        swal({
          title: `You want to edit ${row.name}`,
          buttonsStyling: false,
          confirmButtonClass: 'btn btn-info btn-fill'
        });
      },
      handleDelete (index, row) {
        swal({
          title: 'Are you sure?',
          text: `You won't be able to revert this!`,
          type: 'warning',
          showCancelButton: true,
          confirmButtonClass: 'btn btn-success btn-fill',
          cancelButtonClass: 'btn btn-danger btn-fill',
          confirmButtonText: 'Yes, delete it!',
          buttonsStyling: false
        }).then((result) => {
          if(result.value){
            this.deleteRow(row);
            swal({
              title: 'Deleted!',
              text: `You deleted ${row.name}`,
              type: 'success',
              confirmButtonClass: 'btn btn-success btn-fill',
              buttonsStyling: false
            })
          }
        });
      },
      deleteRow(row){
        let indexToDelete = this.tableData.findIndex((tableRow) => tableRow.id === row.id);
        if (indexToDelete >= 0) {
          this.tableData.splice(indexToDelete, 1)
        }
      }
    },
    mounted () {
      // Fuse search initialization.
      this.fuseSearch = new Fuse(this.tableData, {keys: ['name', 'email'], threshold: 0.3})
    },
    watch: {
      /**
       * Searches through the table data by a given query.
       * NOTE: If you have a lot of data, it's recommended to do the search on the Server Side and only display the results here.
       * @param value of the query
       */
      searchQuery(value){
        let result = this.tableData;
        if (value !== '') {
          result = this.fuseSearch.search(this.searchQuery)
        }
        this.searchedData = result;
      }
    }
  }
</script>
<style>
</style>

