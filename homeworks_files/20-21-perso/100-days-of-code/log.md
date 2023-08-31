# 100 Days Of Code - Log

### Day 22 : Friday 26, november 2021

**Today's progress** : Created commands to manage mangas stored in db from the shell, without needing a front-end. I've implemented the command to create and check a serie's informations. Also added launch.json file.

**Thoughts:** Today is black friday. I haven't bought anything, today was just work, as usual.

### Day 21 : Thursday 25, november 2021

**Today's progress** : Refactoring, again. Also added php-cs-fixer in the workspace so I can make my code conform for the PSR-12 specs.

**Thoughts:** I think I'm okay with refactoring. The code don't haves a perfect architecture but is still okay.

### Day 20 : Wednesday 24, november 2021

**Today's progress** : Refactoring. Learned a bit about php namespaces (PSR-4) and how to properly implement php traits.

**Thoughts:** So I think after finishing the ScanModel, I had to do some refactoring. Currently, idk if I should waste my time for a good architecture rather than working on a new feature ?

### Day 19 : Tuesday 23, november 2021

**Today's progress** : Create volumes implementation + getting chapters of serie with their volumes.

**Thoughts:** The model is finished ! Also, I just had my first job interview for my internship at a SI service company. I think my pitch was good.

### Day 18 : Sunday 21, november 2021

**Today's progress** : major refactoring (get rid of dollar signs in db) and finished arrayList implementation

**Thoughts:** Near the end of this model :eyes: I still have to create volumes and this will be done !

### Day 17 : Saturday 20, november 2021

**Today's progress** : Created tests to implement ArrayList ordering.

### Day 16 : Thursday 18, november 2021

**Today's progress** : Bugfixes and major refactoring

**Thoughts:** Actually very busy...

### Day 15 : Wednesday 17, november 2021

**Today's progress** : Totally implemented LinkedList ordering

**Thoughts:** wooow finished the most difficult I guess... (?)

### Day 14 : Tuesday 16, november 2021

**Today's progress** : improvements, created methods to get and change order of chapters of series

**Thoughts:** Made major changes, but most things are unfinished. I'll finish this tomorrow.

### Day 12-13 (between 22:30 to 00:30) : Sunday 14 and 15, november 2021

**Today's Progress**: 
- Add some methods related to chapters. The SQL linkedList has been implemented (create + read + delete) [49af27a](https://github.com/Kagescan/kagescan-cms/commit/49af27a)
- Add tests related to get or create chapters. [72d472a](https://github.com/Kagescan/kagescan-cms/commit/72d472a)

**Thoughts:** worked during night again due to being late.


### Day 10-11 : Friday 12 and Saturday 13, november 2021

**Today's Progress**: 
- splitted a big file of tests in separate files using traits and require_once functions [f79c9d2...26fd50e](https://github.com/Kagescan/kagescan-cms/compare/f79c9d2c2b53...26fd50e9f7f0)

**Thoughts:** worked well but fogot to write the logs... I didn't worked in friday since it's difficult for me to get time at the evening.

### Day 9 : Thursday 11, november 2021

**Today's Progress**: 
- Fixed for real the bug from yesterday without quickfixes. It was due to configuration problem that involved cache and databases prefixes. [e3c124f](https://github.com/Kagescan/kagescan-cms/commit/e3c124f)
- Implemented three unit tests and improved the existing one. [1ddc36c](https://github.com/Kagescan/kagescan-cms/commit/1ddc36c)
- Made some minor edits [f79c9d2](https://github.com/Kagescan/kagescan-cms/commit/f79c9d2)


**Thoughts:** It is a public holiday today (1918 armistice). I took the opportunity to take a *vacation* during this *afternoon*, which is why there is not as much code as expected.




![image](https://user-images.githubusercontent.com/28659185/141378809-dbe33c2e-a0fa-4942-a57b-e652ce1a1698.png)

*day 8 :*
Forgot to save the md file 🤦 providing an image instead.

### Day 6-7 : Monday 08 and Tuesday 09, November 2021
  
**Today's Progress**: 
- Upgraded the framework from v4.1.3 to 4.1.5 [e47428c](https://github.com/Kagescan/kagescan-cms/commit/e47428c61dba42c36e3d13dec45670d0042ece15)
- Added a support for PhpUnit + wrote one test case (that don't works yet, but runs well). [3b961bc](https://github.com/Kagescan/kagescan-cms/commit/3b961bc8b68991150889a65c470c4209f0e34c4c)
- Made a twitter to discord webhook using IFTTT.

**Thoughts:** Missed Monday due to homeworks, but I caught up my delay by working up to 3 hours today

### Day 4-5 (between 23:30 to 03:00) : Saturday 06 and Sunday 07, November 2021

<table><tr><td>
  
**Today's Progress**: 
- Created a controller and a custom model for the manga section of the CMS [17f6348](https://github.com/Kagescan/kagescan-cms/commit/17f6348689c694bbcafa8e799b409f4f7fbe3027)
- Created a function that generates or reset required tables. [0e37e24 Line 21](https://github.com/Kagescan/kagescan-cms/commit/0e37e240f8981c81cc788d5f0f4b62e7824e55df#diff-5e0bdc442f7ff4e91c3d3d3493cbcbb400f8ece67164e52f2dd6b67980b736d0R27)
- Created SQL snippets to add, check and remove elements of a linked list [0e37e24 Line 81](https://github.com/Kagescan/kagescan-cms/commit/0e37e240f8981c81cc788d5f0f4b62e7824e55df#diff-5e0bdc442f7ff4e91c3d3d3493cbcbb400f8ece67164e52f2dd6b67980b736d0R81)

**Thoughts:** over 160 lines treated today 😊 Nice progress !

</td><td  align="center">
<img src="https://user-images.githubusercontent.com/28659185/140630026-347e5f0d-d780-4a32-8b84-245bd186a920.png" alt="Notion kanban board"/>
<em>Image : a portion of `scanModel` class. </em>
</td></tr>
</table>

### Day 3: Friday November 05, 2021

<table><tr><td>
  
**Today's Progress**: 
* Created a [Kanban board in Notion](https://www.notion.so/kagescan/bdd40eae13534f03aac7535138abdf4f?v=b8e3c9c43a9e4a89b7a82fdffddd9671) and made some progress to organize the team
* Implemented the sql creation table script for the manga section : [6eb9576 and a68930a](https://github.com/Kagescan/kagescan-cms/compare/0d40643..a68930a)
  
**Thoughts:** Kanban boards are very nice to set-up tasks and deadlines.  
  Even though Notion isn't the best app for doing this sort of thing, the integration with pages is pretty cool, we can link a task to a doc spec for example.

</td><td  align="center">
<img src="https://user-images.githubusercontent.com/28659185/140577733-856d4fb7-8e57-4d9f-9cf4-bc96b8101741.png" alt="Notion kanban board"/>
<em>Image : Our kanban board in Notion</em>
</td></tr>
</table>

### Day 2: Thursday November 04, 2021

<img src="https://user-images.githubusercontent.com/28659185/140426504-40a3a5cd-0672-4b98-8199-85947fe08f18.png" alt="discord reminder using github actions" align="right" width="300" />

**Today's Progress**: 

* Made some progress with Notion, and [a question I had with routing](https://forum.codeigniter.com/thread-79952.html)
* Set up my coding workspace and edited the docs about cloning the repo with symlinks : [0d40643](https://github.com/Kagescan/kagescan-cms/commit/0d406434804b2b105f6007d76c09dac05aae9fa3)
* Found a way to store my ordered dataset in MYSQL in discord [private discord thread link](https://discord.com/channels/306353736249245697/453984845123551235/905931581682753596)


**Thoughts:** : I had a dilemna about how to store ordered data in PHP : Using Json or SQL ? I finally managed to solve this using a linked list pattern and backend processing (instead of DBMS sorting).

### Day 1: Wednesday November 03, 2021
**Today's Progress**: 
* Add a github action that sends a discord ping, to remind me to work on it + keep the discord thread opened. 
* Finish writing the front-end specs for the site sections, user POV
* Gathered the team in the discord server of my uni. programming club ([@CPU-Paris](https://github.com/CPU-Paris))

<img src="https://user-images.githubusercontent.com/28659185/140196893-b6086e20-91c3-4e76-8968-c54a4eb70608.png" alt="discord reminder using github actions" width="500"/>

**Thoughts:** : I really like how I programmed that discord reminder. It's a github workflow inside this repo that sends a post request to a custom webhook payload stored in a safe place. It's not the best way, but it's surely original !


**Link to work:** [Notion workspace](https://kagescan.notion.site/f8394b69073e48e79339548e0174a41b?v=f250e6644bc54701bd57256c4674e148) and [redminder.yml github action file](https://github.com/LoganTann/100-days-of-code/blob/master/.github/workflows/reminder.yml)


### Day 0: Tuesday November 02, 2021

**Today's Progress**: Today I'm starting the 100 days of code challenge for [Kagescan-cms](https://github.com/kagescan/kagescan-cms).  
I created a Notion workspace and started writing some specifications for the scan part of the CMS. This is a productivity tool I discovered today.

**Thoughts:** Every programmers knows this is always good to write down a complete specification documentation if you're going to work with a team on a project you owns.  So I need to finish it before starting to code.

**Link to work:** [Notion workspace](https://kagescan.notion.site/Kagescan-cms-e6699487516749708d27b9b3e16f4e2c)
