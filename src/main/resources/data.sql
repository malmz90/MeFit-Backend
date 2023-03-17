INSERT INTO "user" ( is_admin, is_contributor,password,username) VALUES (TRUE,FALSE,'123','Malmz');
INSERT INTO "user" ( is_admin, is_contributor,password,username) VALUES (FALSE,FALSE,'123','Bert');

INSERT INTO profile (disabilities,height,medical_conditions,weight,user_id) VALUES ('one_leg',160,'none',50,1);
INSERT INTO profile (disabilities,height,medical_conditions,weight,user_id) VALUES ('none',192,'none',88,2);

INSERT INTO program (category,name) VALUES ('Muscle Building','Big Biceps');
INSERT INTO program (category,name) VALUES ('Cardio and Muscle building','Run Forest and get strong');

INSERT INTO goal (achieved,end_date,start_date,profile_id,program_id) VALUES (TRUE,'February','Mars',1,1);
INSERT INTO goal (achieved,end_date,start_date,profile_id,program_id) VALUES (TRUE,'June','October',2,2);

INSERT INTO workout (name,type) VALUES ('Weight lifting','Strength');
INSERT INTO workout (name,type) VALUES ('Spinning and lifting','Cardio, Strength');

INSERT INTO program_workout (workout_id, program_id) VALUES (1,1);
INSERT INTO program_workout (workout_id, program_id) VALUES (2,2);

INSERT INTO exercise (description, name) VALUES ('Pushups are done in prone position with palms under shoulders, balls of feet on the ground. The body is pushed up and down with arms straightening and bending alternately, while keeping the back straight.','Push up');
INSERT INTO exercise (description, name) VALUES ('Curl a dumbbell up and down','Bicep curls');
INSERT INTO exercise (description, name) VALUES ('Pedal spinning bike','Spinning');

INSERT INTO workout_exercise (workout_id, exercise_id) VALUES (1,1);
INSERT INTO workout_exercise (workout_id, exercise_id) VALUES (1,2);
INSERT INTO workout_exercise (workout_id, exercise_id) VALUES (2,1);
INSERT INTO workout_exercise (workout_id, exercise_id) VALUES (2,2);

INSERT INTO set (exercise_repetitions) VALUES ('10');
INSERT INTO set (exercise_repetitions) VALUES ('20');
INSERT INTO set (exercise_repetitions) VALUES ('30 minutes');

INSERT INTO exercise_set (set_id,exercise_id) VALUES (1,1);
INSERT INTO exercise_set (set_id,exercise_id) VALUES (2,2);
INSERT INTO exercise_set (set_id,exercise_id) VALUES (3,3);




