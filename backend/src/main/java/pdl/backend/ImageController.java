package pdl.backend;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import net.imglib2.Dimensions;
import net.imglib2.img.array.ArrayImgFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.scif.FormatException;
import io.scif.img.SCIFIOImgPlus;
import net.imglib2.type.numeric.integer.UnsignedByteType;

@RestController
public class ImageController {

  @Autowired
  private ObjectMapper mapper;

  private final ImageDao imageDao;

  @Autowired
  public ImageController(ImageDao imageDao) {
    this.imageDao = imageDao;
  }


  @RequestMapping(value = "/images/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<byte[]> getImage(@PathVariable("id") long id) throws IOException, FormatException {
    Optional<Image> img = imageDao.retrieve(id);
    if (!img.isEmpty()) {
      InputStream inputStream = new ByteArrayInputStream(img.get().getData());
      new InputStreamResource(inputStream);
      byte[] bytes = StreamUtils.copyToByteArray(inputStream);
      return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }


  @RequestMapping(value = "/images/a{id}/{x}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<byte[]> imageProcess(@PathVariable("id") long id,@PathVariable("x")int x) throws IOException, FormatException {
    Optional<Image> img = imageDao.retrieve(id);
    if (!img.isEmpty()) {
      SCIFIOImgPlus<UnsignedByteType> i= ImageConverter.imageFromJPEGBytes(img.get().getData());
      ImageProcessing.luminosite2(i, x);
      byte[] b = ImageConverter.imageToJPEGBytes(i);
      return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(b);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  @RequestMapping(value = "/images/filter{id}/{h}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<byte[]> filterColor(@PathVariable("id") long id,@PathVariable("h")int h) throws IOException, FormatException {
    Optional<Image> img = imageDao.retrieve(id);
    if (!img.isEmpty()) {
      SCIFIOImgPlus<UnsignedByteType> i= ImageConverter.imageFromJPEGBytes(img.get().getData());
      ImageProcessing.hueModification(i, h);
      byte[] b = ImageConverter.imageToJPEGBytes(i);
      return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(b);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  @RequestMapping(value = "/images/filtermean{id}/{m}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<byte[]> filterMean(@PathVariable("id") long id,@PathVariable("m") int m) throws IOException, FormatException {
    Optional<Image> img = imageDao.retrieve(id);
    if (!img.isEmpty()) {
      SCIFIOImgPlus<UnsignedByteType> i= ImageConverter.imageFromJPEGBytes(img.get().getData());
      ImageProcessing.meanFilterSimple(i, m);
      byte[] b = ImageConverter.imageToJPEGBytes(i);
      return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(b);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  @RequestMapping(value = "/images/grayfilter{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<byte[]> GrayFilter(@PathVariable("id") long id) throws IOException, FormatException {
    Optional<Image> img = imageDao.retrieve(id);
    if (!img.isEmpty()) {
      SCIFIOImgPlus<UnsignedByteType> i= ImageConverter.imageFromJPEGBytes(img.get().getData());
      ImageProcessing.GrayFilter(i);
      byte[] b = ImageConverter.imageToJPEGBytes(i);
      return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(b);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value = "/images/bluefilter{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<byte[]> BlueFilter(@PathVariable("id") long id) throws IOException, FormatException {
    Optional<Image> img = imageDao.retrieve(id);
    if (!img.isEmpty()) {
      SCIFIOImgPlus<UnsignedByteType> i= ImageConverter.imageFromJPEGBytes(img.get().getData());
      ImageProcessing.blueFilterWithLoopBuilder(i);
      byte[] b = ImageConverter.imageToJPEGBytes(i);
      return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(b);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value = "/images/redfilter{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<byte[]> RedFilter(@PathVariable("id") long id) throws IOException, FormatException {
    Optional<Image> img = imageDao.retrieve(id);
    if (!img.isEmpty()) {
      SCIFIOImgPlus<UnsignedByteType> i= ImageConverter.imageFromJPEGBytes(img.get().getData());
      ImageProcessing.redFilterWithLoopBuilder(i);
      byte[] b = ImageConverter.imageToJPEGBytes(i);
      return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(b);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  @RequestMapping(value = "/images/greenfilter{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<byte[]> GreenFilter(@PathVariable("id") long id) throws IOException, FormatException {
    Optional<Image> img = imageDao.retrieve(id);
    if (!img.isEmpty()) {
      SCIFIOImgPlus<UnsignedByteType> i= ImageConverter.imageFromJPEGBytes(img.get().getData());
      ImageProcessing.greenFilterWithLoopBuilder(i);
      byte[] b = ImageConverter.imageToJPEGBytes(i);
      return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(b);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  @RequestMapping(value = "/images/border{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<byte[]> Borders(@PathVariable("id") long id) throws IOException, FormatException {
    Optional<Image> img = imageDao.retrieve(id);
    
    if (!img.isEmpty()) {
      SCIFIOImgPlus<UnsignedByteType> i= ImageConverter.imageFromJPEGBytes(img.get().getData());
     
     
      SCIFIOImgPlus<UnsignedByteType> j= ImageConverter.imageFromJPEGBytes(img.get().getData());
      ImageProcessing.borders(i, j);
      byte[] b = ImageConverter.imageToJPEGBytes(j);
      return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(b);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  @RequestMapping(value = "/images/pixelisation{id}/{m}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<byte[]> Pixelisation(@PathVariable("id") long id,@PathVariable("m") int m) throws IOException, FormatException {
    Optional<Image> img = imageDao.retrieve(id);
    
    if (!img.isEmpty()) {
      SCIFIOImgPlus<UnsignedByteType> i= ImageConverter.imageFromJPEGBytes(img.get().getData());
      ImageProcessing.pixelisation(i, m);
      byte[] b = ImageConverter.imageToJPEGBytes(i);
      return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(b);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value = "/images/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteImage(@PathVariable("id") long id) {
    Optional<Image> img = imageDao.retrieve(id);

    if (!img.isEmpty()) {
      imageDao.delete(img.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value = "/images/", method = RequestMethod.POST)
  public ResponseEntity<?> addImage(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
      throws IOException {
    if (!file.getContentType().equals("image/jpeg")) return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    if (!file.isEmpty()){
      Image img = new Image(file.getOriginalFilename(), file.getBytes(), file.getContentType());
      imageDao.create(img);
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value = "/images", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
  @ResponseBody
  public ArrayNode getImageList() {
    ArrayNode nodes = mapper.createArrayNode();
    for (Image img : imageDao.retrieveAll() ){
      ObjectNode node = JsonNodeFactory.instance.objectNode();
      node.put("id", (int)img.getId());
      node.put("name", img.getName());
      node.put("type", img.getType());
      node.put("dimensions", (int)img.getId());
      nodes.insert((int)img.getId(), node);
    }
    return nodes;
  }
}
