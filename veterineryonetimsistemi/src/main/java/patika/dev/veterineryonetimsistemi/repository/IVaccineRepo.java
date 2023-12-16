package patika.dev.veterineryonetimsistemi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import patika.dev.veterineryonetimsistemi.entity.Vaccine;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IVaccineRepo extends JpaRepository<Vaccine, Long> {

    Optional<Vaccine> findByNameAndCodeAndProtectionFinishDate(String name, String code, LocalDate ProtectionFinishDate);

    //Eğer hastaya ait aynı tip aşının (adı ve kodu aynı olan aşı) aşı koruyuculuk bitiş tarihi daha gelmemiş ise sisteme yeni aşı girilememelidir.
    // Aşı kodlarından ve aşı bitiş tarihlerinden bunu kontrol edebilirsin.
    List<Vaccine> findByCodeAndAnimalIdAndProtectionFinishDateAfter(String code, Long animal_id, LocalDate protection_start_date);


    //Hayvan id’sine göre belirli bir hayvana ait tüm aşı kayıtlarını listelemek için gerekli API end point'ini oluşturmak.
    List<Vaccine> findByAnimalId(Long animal_id);


    // Kullanıcının aşı koruyuculuk bitiş tarihi yaklaşan hayvanları listeleyebilmesi için kullanıcının gireceği başlangıç ve
    // bitiş tarihlerine göre aşı koruyuculuk tarihi bu aralıkta olan hayvanların listesini geri döndüren API end  point'ini oluşturmak.
    @Query(value = "SELECT * FROM vaccine WHERE protection_finish_date BETWEEN ?1 AND ?2 ", nativeQuery = true)
    List<Vaccine> findSaveStartAndFinishDate(LocalDate startDate, LocalDate finishDate);

}
