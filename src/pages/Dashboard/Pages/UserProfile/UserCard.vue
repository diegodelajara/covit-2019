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
          <div v-if="userImagesRef">
            <div v-for="(userImage, key) in userImagesRef" :key="key">
              <div v-if="userImage.email != null">
                <img
                  v-if="userImage.email == email"
                  class="avatar border-gray"
                  alt="Avatar"
                  :src="userImage.image"
                  @click="selectUploadFile(userImage['.key'])" />

                
              </div>
            </div>
            <img class="avatar border-gray" v-if="!isExist()" src="@/assets/img/mike.jpg" alt="Avatar" @click="selectUploadFile(null)">
          </div>
          <p class="loading">{{ progressUpload }}%</p>
          <h5 class="title">{{ name }} {{ lastName }}</h5>
        </a>
        <input
          v-show="false"
          id="files"
          type="file"
          name="file"
          ref="uploadInput"
          accept="image/*"
          :multiple="false"
          @change="detectFiles($event)" />


        <!-- <p class="description">
          {{ email }}
        </p> -->
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
import firebase from "firebase/app";
import { getUserFromLocalStorage } from "src/utils/auth";
import { firebaseStorage } from "src/firebase/firebaseStorage";
import { userImagesRef, db } from "src/firebase/firebase";

export default {
  firebase: {
    userImagesRef
  },
  data() {
    return {
      name: null,
      lastName: null,
      email: null,
      elementId: null,
      progressUpload: 0,
      file: {
        downloadURL: "",
        fileName: "",
        size: null,
        type: null,
        uploading: false,
        uploadEnd: false
      },
      uploadTask: "",
      userImage: {
        email: "",
        image: ""
      },
      firebaseUserImages: []
    };
  },
  mounted() {
    this.name = getUserFromLocalStorage().name;
    this.lastName = getUserFromLocalStorage().lastName;
    this.email = getUserFromLocalStorage().email;
    this.images = this.userImagesRef
  },
  updated() {
    if (userImagesRef) {
      for (const i in this.userImagesRef) {
        this.firebaseUserImages.push(this.userImagesRef[i]);
      }
    }
  },
  methods: {
    selectUploadFile(image) {
      this.elementId = image
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
    },
    isExist : function(){
      for(var i=0; i < this.userImagesRef.length; i++){
        if( this.userImagesRef[i].email == this.email){
          return true
        }
      }
      return false
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
            this.userImage.image = downloadURL;
            this.userImage.email = this.email;

            if (this.elementId) {
              db.ref('userImages/' + this.elementId).update({
                image: this.userImage.image
              })
            } else 
              userImagesRef.push(this.userImage);


            // db.ref("userImages/").on("value", snap => {
            //   this.tareas = [];
            //   let obj = snap.val();
            //   for (const i in obj) {
            //     this.tareas.unshift({
            //       ".key": i,
            //       email: obj[i].email,
            //       image: obj[i].image
            //     });
            //   }
            // });
          });
        }
      );
    }
  }
};
</script>
<style>
</style>
