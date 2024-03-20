Veteriner Yönetim Sistemi
Veteriner yönetim sistemi projesi ile bir veteriner kliniğinin kendi işlerini yönetebildiği API 

Bu uygulama ile çalışan sisteme veteriner doktorları kaydedecek,doktorların çalışma günlerini (müsait günlerini) kaydedecek, saat olmadan tarih olarak kayıt yapılacak,

müşterileri kaydedecek,müşterilere ait hayvanları kaydedecek,hayvanlara uygulanmış aşıları tarihleriyle birlikte kaydedecek,
hayvanlar için veteriner hekimlere randevu oluşturacaklar,randevu oluştururken tarih ve saat girilecek,
randevu oluştururken hem doktorun müsait günlerinden saat olmadan kontrol yapılmalı hem de randevu kayıtlarından tarih ve saat ile kontrol yapılmalı. 
Kayıtlarda çakışma olmadığı durumda randevu oluşturulmamaktadır.
(UML diyagramı aşağıdaki linkten  görebilirsiniz.)
https://lucid.app/lucidchart/a4a9b16f-8aa7-440b-bdca-a7d848f2d1d0/edit?viewport_loc=-1231%2C-534%2C5284%2C2394%2CHWEp-vi-RSFO&invitationId=inv_8cb64469-f396-4935-ab34-39b285005f71
Hayvanların ve Sahiplerinin (customer) Yönetimi

Hayvanları kaydetme, bilgilerini güncelleme, görüntüleme ve silme

Hayvan sahiplerini kaydetme, bilgilerini güncelleme, görüntüleme ve silme

Hayvan sahipleri isme göre filtrelenecek şekilde end point oluşturmakta.

Hayvanlar isme göre filtrelenecek şekilde end point oluşturmakta.

Hayvan sahibinin sistemde kayıtlı tüm hayvanlarını görüntülemek için API end point'ini oluşturmak. Hayvan sahibine göre hayvanlara filtreleme yapılmakta.

Uygulanan Aşıların Yönetimi

Hayvanlara uygulanan aşıları kaydetme, bilgilerini güncelleme, görüntüleme ve silme

Eğer hastaya ait aynı tip aşının (adı ve kodu aynı olan aşı) aşı koruyuculuk bitiş tarihi daha gelmemiş ise sisteme yeni aşı girilememelidir.

Hayvan id’sine göre belirli bir hayvana ait tüm aşı kayıtlarını listelemek için gerekli API end point'ini oluşturmakta.

Kullanıcının aşı koruyuculuk bitiş tarihi yaklaşan hayvanları listeleyebilmesi için kullanıcının gireceği başlangıç ve bitiş tarihlerine göre aşı koruyuculuk tarihi bu aralıkta olan hayvanların listesini geri döndüren API end  point'ini oluşturmaktadır.

Randevu Yönetimi

Hayvanların aşı ve muayene randevularının oluşturulması, bilgilerinin güncellenmesi, görüntülenmesi ve silinmesi

Randevular sisteme tarih ve saat içerecek şekilde kaydedilmelidir. Bunun için LocalDateTime kullanılmalıdır.

Hayvanların her türlü muayenesi için doktorlardan uygun tarihlerde ve saatlerde randevu oluşturulmalıdır. Her doktor için sadece saat başı randevu oluşturulabilir. Bir muayenenin sabit olarak bir saat süreceğini kabul edin.

Randevu kaydı oluştururken doktorun girilen tarihte müsait günü olup olmadığı eğer ki müsait günü varsa randevu kayıtlarında girilen saatte başka bir randevusu olup olmadığı kontrol edilmelidir. Her iki durum şartı sağlanırsa randevu oluşturulmalıdır. Şart sağlanmaz ise “throw new RuntimeException("Doktor bu tarihte çalışmamaktadır!/Girilen saatte başka bir randevu mevcuttur.");” gibi hata mesajı fırlatılmalıdır.

Randevular kullanıcı tarafından girilen tarih aralığına ve doktora göre filtrelenmelidir. Buna ait API end point’i oluşturulmalıdır. (randevu için kliniği arayan müşterilerin doktor ve gün taleplerinde uygunluk olup olmadığını sorgulamak için kullanılacaktır.) Jpa’nın findBy between kullanımına bakabilirsin.

Randevular kullanıcı tarafından girilen tarih aralığına ve hayvana göre filtrelenmelidir. Buna ait API end point’i oluşturulmalıdır. Jpa’nın findBy between kullanımına bakabilirsin.

Veteriner Doktor Yönetimi

Veteriner doktorların kaydedilmesi, bilgilerinin güncellenmesi, görüntülenmesi ve silinmesi

Doktorların Müsait Günlerinin Yönetimi

Doktorların müsait günlerini ekleme, bilgilerini güncelleme, görüntüleme ve silme

Doktorun çalıştığı günler sisteme LocalDate olarak kaydedilecektir. Sadece tarih bilgisi olacaktır. Saat, dakika, saniye bilgisi olmayacaktır. 

Veteriner Rapor Yönetimi

randevuya ait rapor düzenlenmekte,

daha sonra da bu rapora ait aşı kaydı yapmaktadır.
