/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Alexandre
 * Created: 04/11/2017
 */

insert into rankode.metric_groups values (null, "Complexidade", "Quantidade de condições e loops aumentam a complexidade");
insert into rankode.metric_groups values (null, "Herança",  "Extends e includes aumentam a herança");
insert into rankode.metric_groups values (null, "Tamanho", "Quantidade de linhas, classes e métodos");
insert into rankode.metric_groups values (null, "Acoplamento", "Quantidade de depenências");

insert into rankode.influences values (null, "Complexidade", "Quantidade de condições e loops aumentam a complexidade");
insert into rankode.influences values (null, "Testabilidade", "Melhora a testabilidade do sistema");
insert into rankode.influences values (null, "Reusabilidade", "Flexibilidade do sistema");
insert into rankode.influences values (null, "Abstração", "Abstração do sistema");
insert into rankode.influences values (null, "Acoplamento", "Quantidade de depenências");
insert into rankode.influences values (null, "Manutenabilidade", "Fácilmente entendível para manutenção");

insert into rankode.targets values (null, "Pacote");
insert into rankode.targets values (null, "Classe");
insert into rankode.targets values (null, "Método");

/*
---------------------------------------- Pacotes ---------------------------------------------------------------->8
*/

insert into rankode.metrics values("CA", 4, 1, "Acoplamento aferente", "Acoplamento aferente",null);
insert into rankode.metrics values("CE", 4, 1, "Acoplamento eferente", "Acoplamento eferente",null);
insert into rankode.metrics values("RMI", 4, 1, "Instabilidade", "Instabilidade",null);
insert into rankode.metrics values("RMA", 4, 1, "Abstração", "Abstração",null);
insert into rankode.metrics values("RMD", 4, 1, "Distância Normalizada", "Distância Normalizada",null);
insert into rankode.metrics values("NOC", 3, 1, "Número de filhos", "Número de filhos",null);
insert into rankode.metrics values("NOI", 3, 1, "Número de interfaces", "Número de interfaces",null);
insert into rankode.metrics values("NOP", 3, 1, "Número de pacotes", "Número de pacotes",null);
insert into rankode.metrics values("TLOC", 3, 1, "Linhas de código", "Linhas de código",null);

/*
---------------------------------------- Classes ---------------------------------------------------------------->8
*/

insert into rankode.metrics values("NSC", 2, 2, "Número de filhos", "Número de filhos",null);
insert into rankode.metrics values("NOF", 3, 2, "Número de atributos", "Número de atributos",null);
insert into rankode.metrics values("NSF", 3, 2, "Número de atributos estáticos", "Número de atributos estáticos",null);
insert into rankode.metrics values("NSM", 3, 2, "Número de métodos estáticos", "Número de métodos estáticos",null);
insert into rankode.metrics values("NORM", 2, 2, "Número de métodos sobrescritos", "Número de métodos sobrescritos",null);
insert into rankode.metrics values("NOM", 3, 2, "Número de métodos", "Número de métodos",null);
insert into rankode.metrics values("DIT", 2, 2, "Profundidade da árvore de herança", "Profundidade da árvore de herança",null);
insert into rankode.metrics values("WMC", 1, 2, "Métodos ponderados por classe", "Métodos ponderados por classe",null);
insert into rankode.metrics values("RFC", 1, 2, "Resposta para uma classe", "É a soma de NOM e NORM",null);
insert into rankode.metrics values("LCOM", 1, 2, "Falta de coesão em métodos", "Falta de coesão em métodos",null);
insert into rankode.metrics values("SIX", 2, 2, "Índice de especialização", "Índice de especialização",null);

/*
---------------------------------------- Métodos ---------------------------------------------------------------->8
*/

insert into rankode.metrics values("MLOC", 3, 3, "Número de Linhas de código do método", "Número de Linhas de código do método",null);
insert into rankode.metrics values("PAR", 3, 3, "Number of Parameters", "Número de atributos",null);
insert into rankode.metrics values("VG", 1, 3, "Complexidade ciclomática", "Complexidade ciclomática de McCabe",null);
insert into rankode.metrics values("NBD", 1, 3, "Profundidade de blocos aninhados", "Profundidade de blocos aninhados",null);