create table curso (id bigint not null, categoria varchar(255), nome varchar(255), primary key (id));
create table resposta (id bigint not null, data_criacao timestamp, mensagem varchar(255), solucao boolean not null,
fk_usuario_id bigint, fk_topico_id bigint, primary key (id));
create table topico (id bigint not null, data_criacao timestamp, mensagem varchar(255),
status varchar(255), titulo varchar(255), fk_usuario_id bigint, fk_curso_id bigint, primary key (id));
create table usuario (id bigint not null, nome varchar(255), senha varchar(255), usuario varchar(255), primary key (id));