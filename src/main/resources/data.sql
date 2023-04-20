insert into categories (id, name)
values (gen_random_uuid(), 'Chirurgia szczękowo-twarzowa'),
       (gen_random_uuid(), 'Neurologia'),
       (gen_random_uuid(), 'Chirurgia ogólna'),
       (gen_random_uuid(), 'Okulistyka'),
       (gen_random_uuid(), 'Onkologia kliniczna'),
       (gen_random_uuid(), 'Kardiologia'),
       (gen_random_uuid(), 'Pediatria'),
       (gen_random_uuid(), 'Psychiatria '),
       (gen_random_uuid(), 'Ortodoncja '),
       (gen_random_uuid(), 'Urologia'),
       (gen_random_uuid(), 'Patomorfologia'),
       (gen_random_uuid(), 'Geriatria'),
       (gen_random_uuid(), 'Epidemiologia'),
       (gen_random_uuid(), 'Hematologia'),
       (gen_random_uuid(), 'Reumatologia'),
       (gen_random_uuid(), 'Zdrowie publiczne'),
       (gen_random_uuid(), 'Neurochirurgia'),
       (gen_random_uuid(), 'Nefrologia'),
       (gen_random_uuid(), 'Intensywna terapia'),
       (gen_random_uuid(), 'Diabetologia'),
       (gen_random_uuid(), 'Endokrynologia'),
       (gen_random_uuid(), 'Kardiochirurgia'),
       (gen_random_uuid(), 'Medycyna pracy'),
       (gen_random_uuid(), 'Dermatologia');


insert into questions (id, name, category_id)
values (gen_random_uuid(), 'Leczenie operacyjne guzów nadnercza należy rozważyć w przypadku:',
        (select id from categories where name = 'Chirurgia ogólna')),
       (gen_random_uuid(), 'Wskaż zestaw zawierający wyłącznie rodzaje operacji bariatrycznych:',
        (select id from categories where name = 'Chirurgia ogólna'));


insert into questions (id, name, category_id)
values (gen_random_uuid(),
        'W której z wymienionych wad wrodzonych serca izolowane postkapilarne nadciśnienie płucne (wartość ciśnienia średniego >20 mm Hg, PAWP >15 mm Hg, PVR <3 WU) jest najmniej prawdopodobne?',
        (select id from categories where name = 'Kardiologia')),
       (gen_random_uuid(),
        'W której z wymienionych wad wrodzonych serca korekcja chirurgiczna niesie największe ryzyko istotnej bradykardii zatokowej?',
        (select id from categories where name = 'Kardiologia'));

insert into questions (id, name, category_id)
values (gen_random_uuid(),
        'Wskaż, jaka jest częstość występowania raka masywu szczękowo-nosowo-sitowego w zatoce szczękowej:',
        (select id from categories where name = 'Chirurgia szczękowo-twarzowa'));

insert into questions (id, name, category_id)
values (gen_random_uuid(), 'Resekcja radykalna szczęki to wycięcie:',
        (select id from categories where name = 'Chirurgia szczękowo-twarzowa'));


insert into answers (id, name, question_id, correct)
values (gen_random_uuid(), '5%', (select id
                                  from questions
                                  where name =
                                        'Wskaż, jaka jest częstość występowania raka masywu szczękowo-nosowo-sitowego w zatoce szczękowej:'),
        false),
       (gen_random_uuid(), '20%', (select id
                                   from questions
                                   where name =
                                         'Wskaż, jaka jest częstość występowania raka masywu szczękowo-nosowo-sitowego w zatoce szczękowej:'),
        false),
       (gen_random_uuid(), '30%', (select id
                                   from questions
                                   where name =
                                         'Wskaż, jaka jest częstość występowania raka masywu szczękowo-nosowo-sitowego w zatoce szczękowej:'),
        true),
       (gen_random_uuid(), '40%', (select id
                                   from questions
                                   where name =
                                         'Wskaż, jaka jest częstość występowania raka masywu szczękowo-nosowo-sitowego w zatoce szczękowej:'),
        false),
       (gen_random_uuid(), '70%', (select id
                                   from questions
                                   where name =
                                         'Wskaż, jaka jest częstość występowania raka masywu szczękowo-nosowo-sitowego w zatoce szczękowej:'),
        false);


