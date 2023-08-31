OpenMascot system

**NOTE : UNFINISHED !!!**

OpenMascot don't have the same behavior of Shimeji, but took a bit of inspiration. The explaination will be a bit messy, I'm sorry...

The mascot have 6 actions, and 2 category of actions : special and normal actions.
Special actions happens when we interact with the mascot and normal actions are controlled by the AI.

Each actions have 2 states : stay and move. Inside the states, you can declare animations.

| Actions          | Stay state                                                   | Move state                                                   |
| ---------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| floor            | when the mascot is bored or resting                          | when the mascot wants to walk/move                           |
| climbing         | when the mascot is resting                                   | when the mascot wants to move                                |
| corner           | some animations of the mascot playing with the corner        | *No moves*                                                   |
| physics (S)      | first animation should happens when the mascot is affected by the gravity, the second is when the mascot is bumping in the ground. | the first animation should happens when the mascot is jumping by his own |
| dragged (S)      | When the mascot is grabbed by the cursor, but is not moving for 2 second | When the mascot is grabbed and the cursor is moving          |
| reproduction (S) | The animation of the parent making a child                   | The entrance animation of the child                          |

The mascot can't move any opened window.

Create OpenMascot animation files
	As the source code says :
	長さ = params duation
	条件 = conditions
	名前 = name
	種類 = type 組み込み = animation type ?
	クラス = className
	移動 = move
	静止 = stay
	固定 = animate ?
	複合 = sequence
	選択 = variable
	bordered action :
		枠 = borderType
		壁 = wall
		地面 = floor
	Breed :
		生まれる場所X = born X
		生まれる場所Y = born y
		生まれた時の行動 = born behavior
	Dragged:
		footX = already translated
	Fall :
		初速X = initial Vx
		初速Y = initial Vy
		空気抵抗X = resistance X
		空気抵抗Y = resistance Y
		重力 = gravity
		IEの端X = IE offset x
		IEの端Y = IE offset Y
	Jump :
		目的地X = target x
		目的地Y = target y
		速度 = velocity
	Look:
		右向き = lookRight
	Sequence :
		繰り返し = loop (param)
	落下する : behavior (name)
-->
