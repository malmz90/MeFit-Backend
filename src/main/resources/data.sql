INSERT INTO "user" ( is_admin, is_contributor,username, first_name, last_name, email) VALUES (TRUE,FALSE,'Malmz','Alexander','Malmqvist', 'malmz@experis.se');
INSERT INTO "user" ( is_admin, is_contributor,username) VALUES (FALSE,FALSE,'Bert');

INSERT INTO profile (disabilities,height,medical_conditions,weight,user_id) VALUES ('one_leg',160,'none',50,1);
INSERT INTO profile (disabilities,height,medical_conditions,weight,user_id) VALUES ('none',192,'none',88,2);

INSERT INTO program (category,name) VALUES ('Muscle Building','Big Biceps');
INSERT INTO program (category,name) VALUES ('Cardio and Muscle building','Run Forest and get strong');

INSERT INTO goal (achieved,end_date,start_date,profile_id) VALUES (FALSE,'February','Mars',1);
INSERT INTO goal (achieved,end_date,start_date,profile_id) VALUES (FALSE,'June','October',2);

INSERT INTO goal_program_association(completed,goal_id,program_id) VALUES (true,1,1);
INSERT INTO goal_program_association(completed,goal_id,program_id) VALUES (false,2,1);
INSERT INTO goal_program_association(completed,goal_id,program_id) VALUES (false,2,2);

INSERT INTO workout (name,type) VALUES ('Functional training legs','Legs');
INSERT INTO workout (name,type) VALUES ('Functional training arms','Arms');
INSERT INTO workout (name,type) VALUES ('Full body workout','Full body');
INSERT INTO workout (name,type) VALUES ('Cardio','Legs');


INSERT INTO program_workout (workout_id, program_id) VALUES (2,1);
INSERT INTO program_workout (workout_id, program_id) VALUES (2,2);
INSERT INTO program_workout (workout_id, program_id) VALUES (4,2);


INSERT INTO exercise (description, name) VALUES ('Push ups are done in prone position with palms under shoulders, balls of feet on the ground. The body is pushed up and down with arms straightening and bending alternately, while keeping the back straight.','Push up');
INSERT INTO exercise (description, name) VALUES ('Curl a dumbbell up and down','Bicep curls');
INSERT INTO exercise (description, name) VALUES ('Pedal spinning bike','Spinning');
INSERT INTO exercise (description, name) VALUES ('Stand with your feet shoulder-width apart, bend your knees and lower your hips, keeping your weight on your heels and your back straight, then push through your heels to straighten your legs and return to the starting position.', 'Squat');
INSERT INTO exercise (description, name) VALUES ('Step forward with one foot and bend your knees, keeping your back straight, until your front thigh is parallel to the ground, then push through your front heel to return to the starting position and repeat on the other side.', 'Lounge');
INSERT INTO exercise (description, name) VALUES ('Run at a speed of at least 6km/h for a minimum of 5 kilometers','Running');

INSERT INTO workout_exercise (workout_id, exercise_id) VALUES (1,4);
INSERT INTO workout_exercise (workout_id, exercise_id) VALUES (1,5);
INSERT INTO workout_exercise (workout_id, exercise_id) VALUES (2,1);
INSERT INTO workout_exercise (workout_id, exercise_id) VALUES (2,2);
INSERT INTO workout_exercise (workout_id, exercise_id) VALUES (3,1);
INSERT INTO workout_exercise (workout_id, exercise_id) VALUES (3,2);
INSERT INTO workout_exercise (workout_id, exercise_id) VALUES (3,3);
INSERT INTO workout_exercise (workout_id, exercise_id) VALUES (3,4);
INSERT INTO workout_exercise (workout_id, exercise_id) VALUES (4,6);



INSERT INTO set (exercise_repetitions) VALUES ('10');
INSERT INTO set (exercise_repetitions) VALUES ('20');
INSERT INTO set (exercise_repetitions) VALUES ('30 minutes');

INSERT INTO exercise_set (set_id,exercise_id) VALUES (1,1);
INSERT INTO exercise_set (set_id,exercise_id) VALUES (2,2);
INSERT INTO exercise_set (set_id,exercise_id) VALUES (3,3);




