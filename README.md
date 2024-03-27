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

## 2. Setup Postgres và PGAdmin trên Docker

```docker
services:
  postgres:
    container_name: postgres
    image: postgres
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: password
      PGDATA: /data/postgres
    volumes:
      - postgres:/data/postgres
    ports:
      - "5432:5432"
    networks:
      - postgres
    restart: unless-stopped
  pgadmin:
    container_name: pgadmin
    image: dpage/pgadmin4
    environment:
      PGADMIN_DEFAULT_EMAIL: ${PGADMIN_DEFAULT_EMAIL:-pgadmin4@pgadmin.org}
      PGADMIN_DEFAULT_PASSWORD: ${PGADMIN_DEFAULT_PASSWORD:-admin}
      PGADMIN_CONFIG_SERVER_MODE: 'False'
    volumes:
      - pgadmin:/var/lib/pgadmin
    ports:
      - "5050:80"
    networks:
      - postgres
    restart: unless-stopped
networks:
  postgres:
    driver: bridge
  spring:
    driver: bridge

volumes:
  postgres:
  pgadmin:
```

1. **Services**:
    - **postgres**: Định nghĩa một dịch vụ PostgreSQL.
        - `container_name`: Đặt tên cho container chứa PostgreSQL là "postgres".
        - `image`: Sử dụng image Docker "postgres" để tạo container.
        - `environment`: Xác định các biến môi trường cho PostgreSQL, bao gồm người dùng (`POSTGRES_USER`), mật khẩu (`POSTGRES_PASSWORD`) và đường dẫn dữ liệu (`PGDATA`).
        - `volumes`: Mount một volume có tên "postgres" vào đường dẫn "/data/postgres" trong container.
        - `ports`: Chuyển tiếp cổng 5432 từ máy host vào cổng 5432 của container, cho phép truy cập PostgreSQL từ bên ngoài.
        - `networks`: Liên kết dịch vụ với mạng có tên "postgres".
        - `restart`: Cấu hình cho container để khởi động lại nếu bị dừng lại, trừ khi container được dừng bởi người dùng.

    - **pgadmin**: Định nghĩa một dịch vụ PGAdmin, một công cụ quản lý cơ sở dữ liệu cho PostgreSQL.
        - `container_name`: Đặt tên cho container chứa PGAdmin là "pgadmin".
        - `image`: Sử dụng image Docker "dpage/pgadmin4".
        - `environment`: Thiết lập các biến môi trường cho PGAdmin, bao gồm email và mật khẩu mặc định cho đăng nhập, cũng như cấu hình server mode.
        - `volumes`: Mount một volume có tên "pgadmin" vào đường dẫn "/var/lib/pgadmin" trong container.
        - `ports`: Chuyển tiếp cổng 5050 từ máy host vào cổng 80 của container để truy cập PGAdmin từ bên ngoài.
        - `networks`: Liên kết dịch vụ với mạng "postgres".
        - `restart`: Cấu hình cho container để khởi động lại nếu bị dừng lại, trừ khi container được dừng bởi người dùng.

2. **Networks**:
    - Định nghĩa hai mạng Docker: "postgres" và "spring". Tuy nhiên, chỉ có mạng "postgres" được sử dụng trong cấu hình dịch vụ.

3. **Volumes**:
    - Định nghĩa hai volumes Docker: "postgres" và "pgadmin". Tuy nhiên, chỉ có volume "postgres" được sử dụng trong cấu hình dịch vụ.

## 3. Tạo service đầu tiên: Customer

