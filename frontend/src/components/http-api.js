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

    getSrcId(){
    },

    updateImage(id){
      const imageUrl = "/images/" + id;
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
      axios.get(imageUrl,{responseType:"blob"}).then(function(response){

        const reader = new window.FileReader();
        reader.readAsDataURL(response.data); 
        reader.onload = function() {
        const imageDataUrl = reader.result;
        imageEl.setAttribute('src', imageDataUrl);
        }
      })
    },

    filtercolor(id,h){
    const imageUrl = "/images/filter" + id+"/"+h;
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

    filtermean(id,m){
      const imageUrl = "/images/filtermean" + id + "/"+m;
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
        url: "/images/" + id ,
        method: 'GET',
        responseType:'blob'
      }).then((response) => {
        var fileUrl = window.URL.createObjectURL(new Blob([response.data]))
        var fileLink = document.createElement('a')
        fileLink.href = fileUrl
        fileLink.setAttribute('download', response.data.name)
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
      })
      .catch(function(){
        console.log('FAILURE!!');
      });
    },

    handleFileUpload(){
      this.file = this.$refs.file.files[0];
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