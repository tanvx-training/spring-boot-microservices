## Dự án Spring Boot Microservices

![Screenshot 2021-11-30 at 12 32 51](https://raw.githubusercontent.com/tanvx-training/spring-boot-microservices/main/images/system_design.png)

## 1. Tạo Project Root

- Sử dụng maven là công cụ build: https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
- Cài đặt và kiểm tra:
  
```
mvn --version
```

- Tạo project bằng command line:

```
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

Trong đó:
- groupId: Định danh duy nhất cho nhóm hoặc tổ chức bạn đang làm việc.
- artifactId: Tên của project bạn muốn tạo.
- archetypeArtifactId: ID của archetype mà bạn muốn sử dụng. Trong trường hợp này, chúng ta sử dụng archetype mặc định maven-archetype-quickstart.
- interactiveMode: Nếu được đặt thành false, Maven sẽ không yêu cầu người dùng nhập thông tin mỗi khi chạy lệnh.
