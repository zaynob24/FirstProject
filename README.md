![Image of Yaktocat](https://camo.githubusercontent.com/37ca472e2afb74974a0314d89af8f470422a79582bed0d188f9927777230195d/68747470733a2f2f6c61756e63682e73612f6173736574732f696d616765732f6c6f676f732f7475776169712d61636164656d792d6c6f676f2e737667)


--------------------------------------------------

# TODO APP

## MY First Project

ToDo List App is a kind of app that generally used to maintain our day-to-day tasks or list everything that we have to do, with the uncompltted tasks at the top of the list, and the completed tasks at the bottom. It is helpful in planning our daily schedules.

--------------------------------------------------------

### technologies used

- ViewModel Architecture
- Room database
- Recyclerview and adapter
- Notification
- Fragment
   - Fragment navigation graph
   - BottomSheetDialogFragment
- Support Library 
   - Material Design (https://material.io/components?platform=android)
   - animatedcheckbox (https://android-arsenal.com/details/1/7205)


---------------------------
## Wireframes

 [open the prototype](https://www.figma.com/proto/CuNBQ8QhQHsR1ejYRNrIUi/Untitled?page-id=0%3A1&node-id=2%3A2&viewport=241%2C48%2C1&scaling=scale-down&starting-point-node-id=2%3A2)
 -----------------------------
<img src="https://d.top4top.io/p_21336dndz1.png" alt="alt text" width="300" height="550"> <img src="https://j.top4top.io/p_2133ve7gn1.png" alt="alt text" width="300" height="550"> <img src="https://d.top4top.io/p_21339qgr41.png" alt="alt text" width="300" height="550">

------------------------

## User Stories
* As User, I want to add list of Todo tasks, so I can easier reach and remember what todo.
* As User, I want to add detailes note to may todo task so that I see clear idea of wht I need todo
* As User, I want to add due date so that I know which task finsh it first
* As User, I want to sprate completed task from not one so that I be more organized
* As User, I want to delete any task so that i don't miss my mind of unwanted tasks 
* As User, I want to indication of whether the task's due date reach or not so that I draw my attention of passed date
* As User, I want to edite any task details so that I feel more in control and don't enforced to create new one
* As User, I want to get notifications when task's due date came so that I never miss doing my task
* As User, I want to hide uncompleted tasks so that I focus on completed one

-------------------------------

## Planning and Development

######  1. Sketch my App Idea
######  2. Do Some Market Research
######  3. Create Mockups of My App And Graphic Design
######  4. developing Front-end
######  4. developing Back-end
######  4. Testing


   I Sketched out my app idea with pen and paper.To make the idea clear. I define how my app will works and what its features are, before I start developing the app.
   Also I download some to do list app to see how it is work and their future.so I open my mind for more ideas.
   
   I use Figma to make my Wireframes and designe, I put a lot of effort in this part .I tried to make my app simple and have good looking, with good user UI experiment .I          take in consideration how the app flow to end up with clear and easy to use app.It wasn't easy To achive all that in xml and android coding (easy on Sketching hard in coding😂😢)
   
   one of the implimntation I stuck with was  to dividing the user tasks based on complete and incomplete ones .I tried to implement it using one recyclerview, but its conditions, and controlling the visibility of the task so missy and difficult 😢.I came up of another way to useing tow recyclerview with one adapter 👏🎉
   
   last but not least, after completing coding and finish the app, I began testing my app with various use cases to debugging
   
   
   ### Problem Solving Strategy
   
   1. Understanding the problem
   2. Breaking the problem down into manageable chunks
   3. Generating ideas for solutions
  
      - trying and debugging
      - make some searching : Google, Stackoverflow , medium.com
      - Ask other developers (if applicable)
      - keep trying and debugging

   --------------------------------------
   
  ### Extra incomplete function to implement in future
  
  I worked in a feature that sorting tasks in categories ,the categories dynamically entered by user.I made database schema(One-to-N Relationships).Also included the categories     menu in layout.
  I faced time problem to cntinue the implimntation part so I plan to doit later.

