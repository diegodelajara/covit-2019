<template>
  <div class="row">
    <!-- <pre>
      {{getEntries}}
    </pre> -->
      <div class="header text-center">
          <!--h3 class="title">Ingresos</h3-->
      </div>
      <div class="col-12">
          <card card-body-classes="table-full-width" no-footer-line>
              <h4 slot="header" class="card-title">Ingresos</h4>
              <div class="">
                <div class="row">
                  <div class="col-lg-4">
                      <n-button class="btn-round btn-primary" type="info" @click.native="modals.addEntry = true">
                      <i slot="label" class="now-ui-icons ui-1_simple-add"></i>
                      Nuevo Ingreso
                      </n-button>
                  </div>
                  <div class="col-lg-4 offset-lg-4">

                      <fg-input>
                        <el-input
                          type="search"
                          class="mb-3"
                          clearable
                          prefix-icon="el-icon-search"
                          style="width: 100%"
                          placeholder="Buscar"
                          v-model="searchQuery"
                          aria-controls="datatables">
                        </el-input>
                      </fg-input>
                  </div>
                </div>
              <el-table stripe
                          style="width: 100%;"
                          :data="getEntries">
                  <el-table-column v-for="(column, i) in tableColumns"
                                  :key="i"
                                  :min-width="column.minWidth"
                                  :prop="column.prop"
                                  :label="column.label">
                  </el-table-column>
                  <el-table-column
                  :min-width="135"
                  fixed="right"
                  label="Actions">
                  <div slot-scope="props" class="table-actions">
                      <n-button @click.native="handleEdit(props.row)"
                              class="like"
                              type="info"
                              size="sm" round icon>
                      <i class="fa fa-pencil"></i>
                      </n-button>
                      <n-button @click.native="handleDelete(props.row)"
                              class="remove"
                              type="danger"
                              size="sm" round icon>
                      <i class="fa fa-trash"></i>
                      </n-button>
                  </div>
                  </el-table-column>
              </el-table>
              </div>
              <div slot="footer" class="col-12 d-flex justify-content-center justify-content-sm-between flex-wrap">
              <!-- <div class="">
                  <p class="card-category">Showing {{from + 1}} to {{to}} of {{total}} entries</p>
              </div>
              <n-pagination class="pagination-no-border"
                              v-model="pagination.currentPage"
                              :per-page="pagination.perPage"
                              :total="total">
              </n-pagination> -->
              </div>
          </card>
      </div>
      <!-- Add Modal -->
      <modal :show.sync="modals.addEntry">
        <h5 slot="header" class="modal-title">Nuevo Ingreso</h5>
        <add-form @on-finish-add-entry="addEntry"/>
      </modal>
      <!-- Fin Add Modal -->

      <!-- Edit Modal -->
      <modal :show.sync="modals.editEntry">
        <h5 slot="header" class="modal-title">Editar Ingreso</h5>
        <edit-form
          @on-finish-edit-entry="editEntry"/>
      </modal>
      <!-- Fin Edit Modal -->
  </div>
</template>

<script>
import axios from "axios"
import { mapGetters, mapMutations, mapActions } from 'vuex'
import { Table, TableColumn, Select, Option } from "element-ui"
import { Pagination as NPagination } from "src/components"
import swal from "sweetalert2"
import Modal from "src/components/Modal"
import AddForm from "src/components/Covit/Contabilidad/AddForm"
import EditForm from "src/components/Covit/Contabilidad/EditForm"

export default {
  name: "Ingresos",
  data () {
    return {
      entriesFromApi: [],
      formData: {
        nombre: null,
        monto: null
      },
      modals: {
        addEntry: false,
        editEntry: false
      },
      pagination: {
        perPage: 5,
        currentPage: 1,
        perPageOptions: [5, 10, 25, 50],
        total: 0
      },
      searchQuery: "",
      propsToSearch: ["nombre"],
      tableColumns: [
        {
          prop: "residence",
          label: "Residencia",
          minWidth: 200
        },
        {
          prop: "gloss",
          label: "gloss",
          minWidth: 200
        },
        {
          prop: "pay_way",
          label: "Forma de pago",
          minWidth: 250
        },
        {
          prop: "date",
          label: "Fecha",
          minWidth: 200
        },
        {
          prop: "price",
          label: "Monto",
          minWidth: 200
        },
        // {
        //   prop: "payer_name",
        //   label: "Nombre",
        //   minWidth: 200
        // },
        // {
        //   prop: "description",
        //   label: "Descripcion",
        //   minWidth: 200
        // },
        // {
        //   prop: "number_pay",
        //   label: "N de pago",
        //   minWidth: 200
        // },
        // {
        //   prop: "receip_number",
        //   label: "receiptNumber",
        //   minWidth: 200
        // },
        // {
        //   prop: "payer_name",
        //   label: "payerName",
        //   minWidth: 200
        // },
        // {
        //   prop: "title",
        //   label: "Nombre",
        //   minWidth: 200
        // },
        // {
        //   prop: "concept",
        //   label: "concept",
        //   minWidth: 200
        // },
        // {
        //   prop: "comments",
        //   label: "comments",
        //   minWidth: 200
        // },{
        //   prop: "respRegister",
        //   label: "respRegister",
        //   minWidth: 200
        // },{
        //   prop: "registrationDate",
        //   label: "registrationDate",
        //   minWidth: 200
        // },{
        //   prop: "status",
        //   label: "status",
        //   minWidth: 200
        // },
        // {
        //   prop: "refDocument",
        //   label: "refDocument",
        //   minWidth: 200
        // }
      ],
      searchedData: []
    }
  },
  components: {
    NPagination,
    [Select.name]: Select,
    [Option.name]: Option,
    [Table.name]: Table,
    [TableColumn.name]: TableColumn,
    Modal,
    AddForm,
    EditForm
  },
  computed: {
    ...mapGetters([
      'getEntries'
    ])
  },
  methods: {
    ...mapActions([
      'getEntriesFromAPI'
    ]),
    ...mapMutations([
      'setEntry'
    ]),
    // Agregar un nuevo ingreso
    async addEntry(newEntry) {
      // await this.$store.commit('setEntry', newEntry)

      // // Cierro el modal
      // this.modals.addEntry = false

      const response = await axios.post('https://my.api.mockaroo.com/ingresos.json?key=66cff0f0&__method=POST', newEntry)
      .then(response => {
        // Cierro el modal
        this.modals.addEntry = false
        // LLamo a todos los ingresos
        this.getEntriesFromAPI()
      })
      .catch(error => console.log(error))


    },
    // Editar un nuevo ingreso
    async editEntry(newEntry) {
      // const response = await axios.put(ENTRIES, newEntry)
      // .then(response => {
      //   // Cierro el modal
      //   this.modals.editEntry = false
      //   // LLamo a todos los ingresos
      //   this.getEntries()
      // })
      // .catch(error => alert(error))
    },
    handleEdit(row) {
      this.modals.editEntry = true

      this.setEntry(row)
    },
    async handleDelete(row) {
      swal({
        title: "¿Estás seguro?",
        text: `No podrás revertir esto luego!`,
        type: "warning",
        showCancelButton: true,
        confirmButtonClass: "btn btn-success btn-fill",
        cancelButtonClass: "btn btn-danger btn-fill",
        confirmButtonText: "Si, borrar!",
        buttonsStyling: false
      }).then(result => {
        if (result.value) {
          this.deleteRow(row)
          swal({
            title: "Borrado!",
            text: `Borraste ${row.nombre}`,
            type: "success",
            confirmButtonClass: "btn btn-success btn-fill",
            buttonsStyling: false
          })
        }
      })
    },
    async deleteRow(row) {
      // row.status = 'ELIMINADO'
      // try {
      //   let response = await axios.put(ENTRIES, row)
      //   await this.getEntries()
      // } catch (error) {
      //   console.log(error)
      // }


    }
  },
  mounted() {
    this.getEntriesFromAPI()
  }
}
</script>

<style lang="scss">
</style>


