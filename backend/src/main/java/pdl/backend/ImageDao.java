package pdl.backend;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDao implements Dao<Image> {

  private final Map<Long, Image> images = new HashMap<>();

  public ImageDao() throws IOException {
    // placez une image test.jpg dans le dossier "src/main/resources" du projet
    final ClassPathResource imgFile = new ClassPathResource("/images");
    byte[] fileContent;
   
    try {
      List<File> resultList = new ArrayList<File>();
      File[] f = imgFile.getFile().listFiles();
      resultList.addAll(Arrays.asList(f));
      for (File file : resultList){
        if (file != null && file.getName().toLowerCase().endsWith(".jpg")  ){
          String files = Files.probeContentType(file.toPath());
          fileContent = Files.readAllBytes(file.toPath());
          Image img = new Image(file.getName(), fileContent, files);
          images.put(img.getId(), img);
        }
      }
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<Image> retrieve(final long id) {
    return Optional.ofNullable(images.get(id));
  }

  @Override
  public List<Image> retrieveAll() {
    List<Image> arrayImage = new ArrayList<Image>();
    images.forEach((id, img) -> {arrayImage.add(img);});
    return arrayImage;
  }

  @Override
  public void create(final Image img) {
    images.put(img.getId(), img);
  }

  @Override
  public void update(final Image img, final String[] params) {
    // Not used
  }

  @Override
  public void delete(final Image img) {
    images.remove(img.getId());
  }
}
