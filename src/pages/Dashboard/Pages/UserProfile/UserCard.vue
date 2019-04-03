<template>
  <card class="card-user">
    <div slot="image" class="image">
      <img
        src="@/assets/img/bg5.jpg"
        alt="Avatar Background">
    </div>
    <div>
      <div class="author">
        <a href="#">
          <img
            v-if="!file.downloadURL"
            class="avatar border-gray"
            src="@/assets/img/mike.jpg"
            alt="Avatar"
            @click="selectUploadFile" />
          <img
            v-else
            class="avatar border-gray"
            :src="file.downloadURL"
            alt="Avatar"
            @click="selectUploadFile" />

          <p class="loading">{{ progressUpload }}%</p>
          <h5 class="title">{{ name }} {{ lastName }}</h5>
        </a>
        <input
          id="files"
          type="file"
          name="file"
          ref="uploadInput"
          accept="image/*"
          :multiple="false"
          @change="detectFiles($event)" />


        <p class="description">
          {{ email }}
        </p>
      </div>
      <!-- <p class="description text-center">
        "Lamborghini Mercy <br>
        Your chick she so thirsty <br>
        I'm in that two seat Lambo"
      </p> -->
    </div>
    <div slot="footer" class="button-container">
      <n-button href="#" type="neutral" icon round size="lg">
        <i class="fa fa-facebook-square"></i>
      </n-button>
      <n-button href="#" type="neutral" icon round size="lg">
        <i class="fa fa-twitter"></i>
      </n-button>
      <n-button href="#" type="neutral" icon round size="lg">
        <i class="fa fa-google-plus-square"></i>
      </n-button>
    </div>
  </card>
</template>
<script>
import { getUserFromLocalStorage } from "src/utils/auth";
import { firebaseStorage } from "src/firebase/firebaseStorage";

export default {
  data() {
    return {
      name: null,
      lastName: null,
      email: null,
      progressUpload: 0,
      file: {
        downloadURL: "",
        fileName: "",
        size: null,
        type: null,
        uploading: false,
        uploadEnd: false
      },
      uploadTask: ""
    };
  },
  mounted() {
    this.name = getUserFromLocalStorage().name;
    this.lastName = getUserFromLocalStorage().lastName;
    this.email = getUserFromLocalStorage().email;
  },
  methods: {
    selectUploadFile() {
      // console.log(this.$refs.uploadInput);
      this.$refs.uploadInput.click();
    },
    detectFiles(e) {
      let fileList = e.target.files || e.dataTransfer.files;
      Array.from(Array(fileList.length).keys()).map(x => {
        this.upload(fileList[x]);
      });
    },
    upload(file) {
      this.file.fileName = file.name;
      this.file.uploading = true;
      this.uploadTask = firebaseStorage
        .ref("profileImages/" + file.name)
        .put(file);
      console.log(this.uploadTask);
    }
  },
  watch: {
    uploadTask: function() {
      this.uploadTask.on(
        "state_changed",
        sp => {
          this.progressUpload = Math.floor(
            (sp.bytesTransferred / sp.totalBytes) * 100
          );
        },
        null,
        () => {
          this.uploadTask.snapshot.ref.getDownloadURL().then(downloadURL => {
            this.file.uploadEnd = true;
            this.file.downloadURL = downloadURL;
            // this.$emit("downloadURL", downloadURL);
          });
        }
      );
    }
  }
};
</script>
<style>
</style>
