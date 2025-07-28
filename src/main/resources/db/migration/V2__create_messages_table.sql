create table messages (
  id varchar(255) primary key,
  content text not null,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
