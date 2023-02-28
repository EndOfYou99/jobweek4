 create table achievements (
       id integer not null,
        achievement varchar(255),
        primary key (id)
    );

    
    
    create table achievements_users (
       achievements_id integer not null,
        users_id integer not null
    ); 

    
    create table game (
       id varchar(255) not null,
        statistics_stat_id integer,
        user_id integer,
        words_id integer,
        primary key (id)
    );

    
    create table monthly_ranks (
       id integer not null auto_increment,
        easy_games integer,
        hard_games integer,
        loses integer,
        medium_games integer,
        played_games integer,
        user varchar(255),
        wins integer,
        primary key (id)
    );

    
    create table ranks (
       id integer not null auto_increment,
        easy_games integer not null,
        hard_games integer not null,
        loses integer not null,
        medium_games integer not null,
        played_games integer not null,
        user varchar(255),
        wins integer not null,
        primary key (id)
    );

    
    
    create table statistics (
       stat_id integer not null auto_increment,
        date date,
        diff integer,
        hidden_word varchar(255),
        max_tries integer,
        result varchar(255),
        tries integer,
        used_letters tinyblob,
        monthly_ranks_id integer,
        ranks_id integer,
        primary key (stat_id)
    );

    
    create table user (
       id integer not null auto_increment,
        password varchar(255),
        username varchar(255),
        primary key (id)
    );

    
    create table user_achievements (
       user_id integer not null,
        achievement_id integer not null
    );
 
    
    create table words (
       id integer not null auto_increment,
        difficulty varchar(255),
        word varchar(255),
        primary key (id)
    );

    
    
    alter table achievements_users 
       add constraint FK2olw586sws2a032ff1a43uj7t 
       foreign key (users_id) 
       references user (id);
 
    
    alter table achievements_users 
       add constraint FKp7kot83kkdnhg8eqriibnhqdx 
       foreign key (achievements_id) 
       references achievements (id);
 
    
    alter table game 
       add constraint FKkqgy3s5im03k9645n5p4766e5 
       foreign key (statistics_stat_id) 
       references statistics (stat_id);
 
    
    alter table game 
       add constraint FKefqw7nsur06wyld1gavn3ic6b 
       foreign key (user_id) 
       references user (id);
 
    
    alter table game 
       add constraint FKa0vw5owq5lkvtswebimt77lwh 
       foreign key (words_id) 
       references words (id);
 
    
    alter table statistics 
       add constraint FKosgkj8b5mlbyi61q68crnnv3c 
       foreign key (monthly_ranks_id) 
       references monthly_ranks (id);
 
    
    alter table statistics 
       add constraint FK2sxasefpwhb0wa5xmd381yqxd 
       foreign key (ranks_id) 
       references ranks (id);
 
    
    alter table user_achievements 
       add constraint FK8ipvec6cs8t3g8515thtlsxuf 
       foreign key (achievement_id) 
       references achievements (id);

    
    alter table user_achievements 
       add constraint FK2gp4wt3thphhd9yucjl9p6jui 
       foreign key (user_id) 
       references user (id);
       
       insert into words (id, difficulty,word) values(1,"easy","Abyss")
,(2,"easy","Bubbly")
,(3,"easy","Buzz")
,(4,"easy","Buff")
,(5,"easy","Cozy")
,(6,"easy","Fluff")
,(7,"easy","Fluffy")
,(8,"easy","Fizz")
,(9,"easy","Fizzy")
,(10,"easy","Fox")
,(11,"easy","Jinx")
,(12,"easy","Lucky")
,(13,"easy","Puzzle")
,(14,"medium","Foxglove")
,(15,"medium","Abruptly")
,(16,"medium","Voyeurism")
,(17,"medium","Pneumonia")
,(18,"medium","Jiujitsu")
,(19,"medium","Espionage")
,(20,"medium","Witchcraft")
,(21,"medium","Razzmatazz")
,(22,"medium","Zigzagging")
,(23,"medium","Buckaroo")
,(24,"medium","Iatrogenic")
,(25,"medium","Jawbreaker")
,(26,"medium","Voodoo")
,(27,"hard","Jazz_singer_drops_beats")
,(28,"hard","Buzzing_around_the_beekeeper")
,(29,"hard","Awkward_klutzy_numbskull")
,(30,"hard","Croquet_players_fix_games")
,(31,"hard","Throwing_gnarly_punches")
,(32,"hard","Absurd_wizard_mystifies")
,(33,"hard","Jiujitsu_masters_train")
,(34,"hard","Wimpy_geek_panics")
,(35,"hard","Twelve_foxes_hunt")
,(36,"hard","A_quiet_jinx_sulks")
,(37,"hard","Nine_tailed_demon_fox")
,(38,"hard","The_great__wall_of_china")
,(39,"hard","Sage_of_six_paths");


insert into achievements (id,achievement) values (1,"Win 5 medium games"),(2,"Win 5 games")
,(3,"Win 5  hard games"),
(4,"Play 10 games");