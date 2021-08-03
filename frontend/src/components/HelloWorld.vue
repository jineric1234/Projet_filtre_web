<template >

  <body class="body" :style="{'background-image':'url(https://cdn.dribbble.com/users/5031/screenshots/3713646/attachments/832536/wallpaper_mikael_gustafsson.png)'}">
  <div id="nav">
    
    <router-link to="/about">Les besoins</router-link>
   <p>Vous pouvez visiter notre gitlab du Cremi <a href="https://gitlab.emi.u-bordeaux.fr/anouamani/proj_filtre-web">Notre GitLab</a> <br />
</p>
  </div>

  <div>
   <img class="logo" alt="Vue logo" src="../assets/Elaboration.png">
   <h1> Welcome to our image processing Application</h1>
  </div>

  <div class="hello">
    <h1>{{ msg }}</h1>

    <!-- actualiser liste d'images-->
    <button class="Search__button" @click="callRestService(), updateImage(0)">Call Spring Boot REST backend</button>
    <br/><br/>

    <!-- liste des images avec atualisation dans img-->
    <div >
      <select v-model="selected">
        <option v-for="item in response" :key="item.id" v-on:click="updateImage(item.id)"> {{ item.name }} </option>
      </select>
      <br/><br/>

      <!-- ajouter une image dans le serveur-->
      <div class="container">
        <div class="large-12 medium-12 small-12 cell">
          <label> Upload a file <br><br>
            <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
          </label>
            <button v-on:click="submitFile();callRestService()">Submit</button>
        </div>
      </div>
    </div>

    <!-- apperçu de l'image -->
    <div class="slider">
      <button @click="prev" class="btn btn-left">
          <img src="../assets/chevron_left.svg" class="icon">
      </button>
      
      <img id="currentImage" src="" class="img-slider">
      <button @click="next" class="btn btn-right">
          <img src="../assets/chevron_right.svg" class="icon">
      </button>
    </div>

    <br><br>

   <!-- <img id ="currentImage" src="">-->
   <div>
    <input v-model="message" placeholder="Put a number between 1 and 10" id="paramvide2">
      <button @click="filtermean(selected_id, message)"> Apply blurry filter</button>
      <img id="currentImage" src="">
      <button @click="downloadmean(selected_id,message)"> Download Image </button>
   </div>
    
   
   <div>
    <input v-model="message" placeholder="Number between -250 and 250" id="paramvide">
      <button @click="lumButton(selected_id,message)"> Apply brightness filter</button>
      <img id="currentImage" src="">
      <button @click="downloadlum(selected_id,message)"> Download Image </button>
   </div>

    <div>
    <input v-model="message" placeholder="Put a number between 0 and 360" id="paramvide1">
      <button @click="filtercolor(selected_id, message)"> Apply color filter</button>
      <img id="currentImage" src="">
      <button @click="downloadcolor(selected_id,message)"> Download Image </button>
   </div>
   <div>
    <input v-model="message" placeholder="Put a number between 1 and 10" id="paramvide3">
      <button @click="pixelisation(selected_id, message)"> Apply pixelation filter</button>
      <img id="currentImage" src="">
      <button @click="downloadpixel(selected_id,message)"> Download Image </button>
   </div>
  <div>
      <button @click="borders(selected_id)"> Apply Border filter</button>
      <img id="currentImage" src="">
      <button @click="downloadborder(selected_id)"> Download Image </button>
   </div>

   

    <div>
      
      <select v-model="selected" multiple>
        
  <option disabled value="">Choisissez</option>
  <option  v-on:click="grayfilter(selected_id) ">Apply GrayFilter</option>
  
  <option v-on:click="bluefilter(selected_id)">Apply BlueFilter</option>
  <option v-on:click="redfilter(selected_id)">Apply RedFilter</option>
  <option v-on:click="greenfilter(selected_id)">Apply GreenFilter</option>

  
</select>

<button @click="downloadgray(selected_id)"> Download gray Image </button>
<button @click="downloadblue(selected_id)"> Download blue Image </button>  
<button @click="downloadred(selected_id)"> Download red Image </button> 
<button @click="downloadgreen(selected_id)"> Download green Image </button> 


    </div>

   <!-- effacer une image de la liste-->
   <button v-on:click="erraseImage(selected_id); callRestService(); rearrange()"> Delete </button>
   <button @click="updateImage(selected_id)"> Original Image </button>
   <button @click="download(selected_id)"> Download original Image </button>

    <br><br><br><br><br>
  </div>
</body>
</template>


<script>
//import Slider from './Slider'
import axios from "axios";
export default {
  name: "HelloWorld",
  
 props: {
    msg: String,
  },
  data() {
    return {
      response: "[]",
      errors: [],
      selected: '',
      selected_id: '',
      files:'',
      index: 0,
      transition: "transform 0,2s ease"
    };
  },
  mounted(){
    this.callRestService();
  },

  methods: {
    callRestService() {
      axios
        .get(`images`)
        .then((response) => {
          // JSON responses are automatically parsed.
          this.response = response.data;
        })
        .catch((e) => {
          this.errors.push(e);
        });
    },

    updateImage(id){
      const imageUrl = "/images/" + id;
      const imageEl = document.getElementById('currentImage');
      this.selected_id = id;
      axios.get(imageUrl, { responseType:"blob" }).then(function (response) {
        const reader = new window.FileReader();
        reader.readAsDataURL(response.data); 
        reader.onload = function() {
        const imageDataUrl = reader.result;
        imageEl.setAttribute('src', imageDataUrl);
        }
      })
    },

    updateImage2(id,x){
      const imageUrl = "/images/a" + id+"/" + x;
      const imageEl = document.getElementById('currentImage');

      axios.get(imageUrl, { responseType:"blob" }).then(function (response) {
        const reader = new window.FileReader();
        reader.readAsDataURL(response.data); 
        reader.onload = function() {
        const imageDataUrl = reader.result;
        imageEl.setAttribute('src', imageDataUrl);
        }
      })
    },

    lumButton(id,x){
      const imageUrl = "/images/a" + id+"/" +x;
      const imageEl = document.getElementById('currentImage');
      const imagePlace = document.getElementById('paramvide').value;
      if(imagePlace == ''){
        alert("No parameter detected!");
        return 0;
      }
      if(imagePlace >250){
        alert("the parameter is greater than 250 !");
        return 0;
      }
       if(imagePlace <(-250)){
        alert("the parameter is lower -250 !");
        return 0
      }
      if((imagePlace <= 250) && (imagePlace >= -250)){
         axios.get(imageUrl,{responseType:"blob"}).then(function(response){

        const reader = new window.FileReader();
        reader.readAsDataURL(response.data); 
        reader.onload = function() {
        const imageDataUrl = reader.result;
        imageEl.setAttribute('src', imageDataUrl);
        }
      })
      }else{
        alert("Incorrect value !");
      }
     
    },

    filtercolor(id,h){
    const imageUrl = "/images/filter" + id+"/"+h;
    const imageEl = document.getElementById('currentImage');
    const imagePlace = document.getElementById('paramvide1').value;
      if(imagePlace == ''){
        alert("No parameter detected!");
        return 0;
      }
      if(imagePlace >360){
        alert("the parameter is greater than 360 !");
        return 0;
      }
       if(imagePlace <0){
        alert("the parameter is lower 0 !");
        return 0;
      }
      if((imagePlace <= 360) && (imagePlace >= 0)){
          axios.get(imageUrl,{responseType:"blob"}).then(function(response){
        const reader = new window.FileReader();
        reader.readAsDataURL(response.data); 
        reader.onload = function() {
        const imageDataUrl = reader.result;
        imageEl.setAttribute('src', imageDataUrl);
        }
      })
      }else{
        alert("Incorrect value !");
      }
  
    },

    filtermean(id,m){
      const imageUrl = "/images/filtermean" + id + "/"+m;
      const imageEl = document.getElementById('currentImage');
      const imagePlace = document.getElementById('paramvide2').value;
      if(imagePlace == ''){
        alert("No parameter detected !");
        return 0;
      }
      if(imagePlace >10){
        alert("the parameter is greater than 10 !");
        return 0;
      }
       if(imagePlace <1){
        alert("the parameter is lower 1 !");
        return 0;
      }
      if((imagePlace <= 10) && (imagePlace >= 1)){
         axios.get(imageUrl,{responseType:"blob"}).then(function(response){

        const reader = new window.FileReader();
        reader.readAsDataURL(response.data); 
        reader.onload = function() {
        const imageDataUrl = reader.result;
        imageEl.setAttribute('src', imageDataUrl);
        }
      })
      }else{
         alert("Incorrect value !");
      }


     
    },
     pixelisation(id,m){
      const imageUrl = "/images/pixelisation" + id + "/"+m;
      const imageEl = document.getElementById('currentImage');
      const imagePlace = document.getElementById('paramvide3').value;
      if(imagePlace == ''){
        alert("No parameter detected !");
        return 0;
      }
      if(imagePlace >10){
        alert("the parameter is greater than 10 !");
        return 0;
      }
       if(imagePlace <1){
        alert("the parameter is lower 1 !");
        return 0;
      }
      if((imagePlace <= 10) && (imagePlace >= 1)){
         axios.get(imageUrl,{responseType:"blob"}).then(function(response){

        const reader = new window.FileReader();
        reader.readAsDataURL(response.data); 
        reader.onload = function() {
        const imageDataUrl = reader.result;
        imageEl.setAttribute('src', imageDataUrl);
        }
      })
      }else{
         alert("Incorrect value !");
      }


     
    },
      grayfilter(id){
      const imageUrl = "/images/grayfilter" + id;
      const imageEl = document.getElementById('currentImage');
         axios.get(imageUrl,{responseType:"blob"}).then(function(response){

        const reader = new window.FileReader();
        reader.readAsDataURL(response.data); 
        reader.onload = function() {
        const imageDataUrl = reader.result;
        imageEl.setAttribute('src', imageDataUrl);
        }
      })
    },

    bluefilter(id){
      const imageUrl = "/images/bluefilter" + id;
      const imageEl = document.getElementById('currentImage');
         axios.get(imageUrl,{responseType:"blob"}).then(function(response){

        const reader = new window.FileReader();
        reader.readAsDataURL(response.data); 
        reader.onload = function() {
        const imageDataUrl = reader.result;
        imageEl.setAttribute('src', imageDataUrl);
        }
      })
    },
    redfilter(id){
      const imageUrl = "/images/redfilter" + id;
      const imageEl = document.getElementById('currentImage');
         axios.get(imageUrl,{responseType:"blob"}).then(function(response){

        const reader = new window.FileReader();
        reader.readAsDataURL(response.data); 
        reader.onload = function() {
        const imageDataUrl = reader.result;
        imageEl.setAttribute('src', imageDataUrl);
        }
      })
    },
    greenfilter(id){
      const imageUrl = "/images/greenfilter" + id;
      const imageEl = document.getElementById('currentImage');
         axios.get(imageUrl,{responseType:"blob"}).then(function(response){

        const reader = new window.FileReader();
        reader.readAsDataURL(response.data); 
        reader.onload = function() {
        const imageDataUrl = reader.result;
        imageEl.setAttribute('src', imageDataUrl);
        }
      })
    },
    borders(id){
      const imageUrl = "/images/border" + id;
      const imageEl = document.getElementById('currentImage');
         axios.get(imageUrl,{responseType:"blob"}).then(function(response){

        const reader = new window.FileReader();
        reader.readAsDataURL(response.data); 
        reader.onload = function() {
        const imageDataUrl = reader.result;
        imageEl.setAttribute('src', imageDataUrl);
        }
      })
    },

    download(id){
      axios({
        url: "/images/" + id,
        method: 'GET',
        responseType:'blob'
      }).then((response) => {
        var fileUrl = window.URL.createObjectURL(new Blob([response.data]))
        var fileLink = document.createElement('a')
        fileLink.href = fileUrl
        fileLink.setAttribute('download', this.selected)
        document.body.appendChild(fileLink)
        fileLink.click()
      })
    },
    downloadlum(id,x){
      axios({
        url: "/images/a" + id+"/" +x,
        method: 'GET',
        responseType:'blob'
      }).then((response) => {
        var fileUrl = window.URL.createObjectURL(new Blob([response.data]))
        var fileLink = document.createElement('a')
        fileLink.href = fileUrl
        fileLink.setAttribute('download', this.selected)
        document.body.appendChild(fileLink)
        fileLink.click()
      })
    },
    downloadcolor(id,x){
      axios({
        url: "/images/filter" + id+"/" +x,
        method: 'GET',
        responseType:'blob'
      }).then((response) => {
        var fileUrl = window.URL.createObjectURL(new Blob([response.data]))
        var fileLink = document.createElement('a')
        fileLink.href = fileUrl
        fileLink.setAttribute('download', this.selected)
        document.body.appendChild(fileLink)
        fileLink.click()
      })
    },
    downloadpixel(id,x){
      axios({
        url: "/images/pixelisation" + id+"/" +x,
        method: 'GET',
        responseType:'blob'
      }).then((response) => {
        var fileUrl = window.URL.createObjectURL(new Blob([response.data]))
        var fileLink = document.createElement('a')
        fileLink.href = fileUrl
        fileLink.setAttribute('download', this.selected)
        document.body.appendChild(fileLink)
        fileLink.click()
      })
    },
    downloadmean(id,x){
      axios({
        url: "/images/filtermean" + id+"/" +x,
        method: 'GET',
        responseType:'blob'
      }).then((response) => {
        var fileUrl = window.URL.createObjectURL(new Blob([response.data]))
        var fileLink = document.createElement('a')
        fileLink.href = fileUrl
        fileLink.setAttribute('download', this.selected)
        document.body.appendChild(fileLink)
        fileLink.click()
      })
    },
    downloadborder(id){
      axios({
        url: "/images/border" + id,
        method: 'GET',
        responseType:'blob'
      }).then((response) => {
        var fileUrl = window.URL.createObjectURL(new Blob([response.data]))
        var fileLink = document.createElement('a')
        fileLink.href = fileUrl
        fileLink.setAttribute('download', this.selected)
        document.body.appendChild(fileLink)
        fileLink.click()
      })
    },
    downloadgray(id){
      axios({
        url: "/images/grayfilter" + id,
        method: 'GET',
        responseType:'blob'
      }).then((response) => {
        var fileUrl = window.URL.createObjectURL(new Blob([response.data]))
        var fileLink = document.createElement('a')
        fileLink.href = fileUrl
        fileLink.setAttribute('download', this.selected)
        document.body.appendChild(fileLink)
        fileLink.click()
      })
    },
    downloadred(id){
      axios({
        url: "/images/redfilter" + id,
        method: 'GET',
        responseType:'blob'
      }).then((response) => {
        var fileUrl = window.URL.createObjectURL(new Blob([response.data]))
        var fileLink = document.createElement('a')
        fileLink.href = fileUrl
        fileLink.setAttribute('download', this.selected)
        document.body.appendChild(fileLink)
        fileLink.click()
      })
    },
    downloadgreen(id){
      axios({
        url: "/images/greenfilter" + id,
        method: 'GET',
        responseType:'blob'
      }).then((response) => {
        var fileUrl = window.URL.createObjectURL(new Blob([response.data]))
        var fileLink = document.createElement('a')
        fileLink.href = fileUrl
        fileLink.setAttribute('download', this.selected)
        document.body.appendChild(fileLink)
        fileLink.click()
      })
    },
    downloadblue(id){
      axios({
        url: "/images/bluefilter" + id,
        method: 'GET',
        responseType:'blob'
      }).then((response) => {
        var fileUrl = window.URL.createObjectURL(new Blob([response.data]))
        var fileLink = document.createElement('a')
        fileLink.href = fileUrl
        fileLink.setAttribute('download', this.selected)
        document.body.appendChild(fileLink)
        fileLink.click()
      })
    },

    submitFile(){
      let formData = new FormData();
      formData.append('file', this.file);
      axios.post('/images/', formData, {
        headers: { 'Content-Type': 'multipart/form-data'}
         
      }).then(function(){
        console.log('SUCCESS!!');
         axios
        .get(`images`)
        .then((response) => {
          // JSON responses are automatically parsed.
          this.response = response.data;
        })
        
      })
      .catch(function(){
        console.log('FAILURE!!');
      });
      
    },

    handleFileUpload(){
      this.file = this.$refs.file.files[0];
    },

rearrange(){
      let cpt=0;
      for(let i=0;cpt<this.nbImg;i++){
        this.updateImage(cpt);
        cpt++;
      }
    },
    erraseImage(id){
      const imageUrl = "/images/" + id;
      const imageEl = document.getElementById('currentImage');
      axios.delete(imageUrl, { responseType:"blob" }).then(function (response) {
        const reader = new window.FileReader();
        reader.readAsDataURL(response.data); 
        reader.onload = function() {
        const imageDataUrl = reader.result;
        imageEl.setAttribute('src', imageDataUrl);
        }
      })
    },
    next(){      
      if(this.index+1>=this.response.length){
        this.index=0;
        this.updateImage(0);
      }else{
      console.log(this.index);
      this.index++;
      this.updateImage(this.index);
      }      
    },
    prev(){
      if(this.index==0){
        this.index=this.response.length;
      }
      this.index--;
      console.log(this.index);
      this.updateImage(this.index);
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
body {
color:black;
background-color:white;
background-image:url(/home/eric/Documents/Projet_logiciel/proj_filtre-web/fondecran.jpg);
background-attachment:fixed;
}
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  
  margin: 0 10px;
}
a {
  color: #42b983;
}

.slider {
    width: 600px;
    height: 400px;
    margin: 100px auto 0;
    /*background: #000;*/
    overflow: hidden;
    position: relative;

}
.img-slider{
  width:600px ;
  height: 600px;
  max-width: 100%;
  max-height: 100%;
  background-size: 800px;
  
}
.btn{
    outline: none;
    border: none;
    width: 60px;
    height: 60px;
    border-radius: 50%;
    display: block;
    position: absolute;
    z-index: 1000;
    cursor: pointer;
    font-size: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
}
.icon{
    width: 30px;
}
.btn-left{
    top: 44%;
    left: 3px;
    transform: translateY(-50);
}
.btn-right{
    top: 44%;
    right: 3px;
    transform: translateY(-50);
}
.logo {
  width: 250px;
  height: 250px;
}
</style>
