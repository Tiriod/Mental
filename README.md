# Mental
2023多媒体设计大赛
---
tips：1.后续需要开发的话，后端的数据可以根据自己需要来整理进行
      2.原版的UI设计图第一版：https://mastergo.com/goto/t04j7QWN?page_id=M&file=98938268498546 邀请您进入《2023多媒体大赛》，点击链接开始协作
        原版的UI设计图第二版（以该版为主）：https://mastergo.com/goto/t04lTrBp?page_id=M&file=100677053462420 邀请您进入《新文件》，点击链接开始协作
      3.现在使用的都是本地的UI组件和资源文件，后续开发根据自己的需要放在服务器进行请求拉取即可
      4.第一次开发这样的项目，有问题请见谅
---
# 文件目录清单

## Adapter

- **ActionAdapter**: 用于操作项列表的适配器。
- **ActionCardAdapter**: 用于操作卡片列表的适配器。
- **ActivityAdapter**: 用于活动项列表的适配器。
- **AudioCardAdapter**: 用于音频卡片列表的适配器。
- **BannerAdapter**: 用于横幅广告列表的适配器。
- **BookAdapter**: 用于书目列表的适配器。
- **CalendarAdapter**: 用于日历视图的适配器。
- **EmotionAdapter**: 用于情绪项列表的适配器。
- **EmotionCardAdapter**: 用于情绪卡片列表的适配器。
- **FoodAdapter**: 用于食物项列表的适配器。
- **FunctionAdapter**: 用于功能模块列表的适配器。
- **HeaderAdapter**: 用于页眉标签的适配器。
- **MeditationClassAdapter**: 用于冥想课程列表的适配器。
- **MeditationIntroduceAdapter**: 用于冥想介绍列表的适配器。
- **ModuleAdapter**: 用于模块列表的适配器。
- **UserCardAdapter**: 用于用户卡片列表的适配器。
- **UserModeAdapter**: 用于用户模式列表的适配器。

## Definition

- **ActionCardItem**: 表示操作卡片的数据模型。
- **ActionItem**: 表示操作项的数据模型。
- **ActivityItem**: 表示活动项的数据模型。
- **AudioCardItem**: 表示音频卡片的数据模型。
- **BookItem**: 表示书目的数据模型。
- **CalendarDay**: 表示日历的日期数据模型。
- **EmotionCardItem**: 表示情绪卡片的数据模型。
- **EmotionItem**: 表示情绪项的数据模型。
- **FoodItem**: 表示食物项的数据模型。
- **FunctionModule**: 表示功能模块的数据模型。
- **UserCardItem**: 表示用户卡片的数据模型。
- **UserModeItem**: 表示用户模式的数据模型。

## FunctionUI

- **AnalyzeActivity**: 用于展示分析功能的活动。
- **FoodActivity**: 用于展示食物功能的活动。
- **GameActivity**: 用于展示游戏功能的活动。
- **MeditationActivity**: 用于展示冥想功能的活动。
- **MeditationAudioActivity**: 用于展示冥想音频功能的活动。
- **NoteActivity**: 用于展示笔记功能的活动。
- **ReadActivity**: 用于展示阅读功能的活动。

## MainUI

- **AIConsultFragment**: 主界面中的 AI 咨询模块片段。
- **HomeFragment**: 主界面中的主页模块片段。
- **MineFragment**: 主界面中的个人中心模块片段。
- **ShareLoopFragment**: 主界面中的分享循环模块片段。

## ModuleUI

- **CameraModuleActivity**: 用于展示相机模块功能的活动。
- **TalkModuleActivity**: 用于展示对话模块功能的活动。
- **TestModuleActivity**: 用于展示测试模块功能的活动。

## MainActivity

- 主应用程序的入口活动。

# Activity 功能描述
## AnalyzeActivity
用于展示心理分析功能的活动。用户可以在这个界面上接受心理状态分析和建议。

## FoodActivity
用于展示食物相关功能的活动。用户可以查看营养信息、食谱、饮食建议等。

## GameActivity
用于展示游戏相关功能的活动。用户可以玩游戏以放松心情或锻炼注意力。

## MeditationActivity
用于展示冥想功能的活动。用户可以选择不同的冥想课程，进行冥想练习。

## MeditationAudioActivity
用于展示冥想音频功能的活动。用户可以播放冥想音频，放松身心。

## NoteActivity
用于展示笔记功能的活动。用户可以记录想法、心情和笔记。

## ReadActivity
用于展示阅读功能的活动。用户可以阅读心理健康、AI 分析等方面的书籍或文章。

## CameraModuleActivity
用于展示相机模块功能的活动。用户可以拍摄照片或录制视频。

## TalkModuleActivity
用于展示对话模块功能的活动。用户可以与 AI 或其他用户进行交流和对话。

## TestModuleActivity
用于展示测试模块功能的活动。用户可以进行心理测试、情绪测试等。

# HomeFragment 功能描述
## AIConsultFragment
主界面中的 AI 咨询模块片段。用户可以与 AI 进行交流，获得心理健康建议和支持。

## HomeFragment
主界面中的主页模块片段。展示用户的主页信息，包括心理状态、提醒事项等。

## MineFragment
主界面中的个人中心模块片段。用户可以查看和编辑个人信息，管理应用设置。

## ShareLoopFragment
主界面中的分享循环模块片段。用户可以分享心情、笔记等内容，与其他用户互动和交流。
