package com.example.mental.MainUI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mental.Adapter.ActivityAdapter;
import com.example.mental.Adapter.BannerAdapter;
import com.example.mental.Adapter.FunctionAdapter;
import com.example.mental.Adapter.MessageAdapter;
import com.example.mental.Adapter.ModuleAdapter;
import com.example.mental.Definition.ActivityItem;
import com.example.mental.Definition.BannerItem;
import com.example.mental.Definition.FunctionModule;
import com.example.mental.Definition.MessageItem;
import com.example.mental.FunctionUI.AnalyzeActivity;
import com.example.mental.FunctionUI.FoodActivity;
import com.example.mental.FunctionUI.GameActivity;
import com.example.mental.FunctionUI.MeditationActivity;
import com.example.mental.FunctionUI.NoteActivity;
import com.example.mental.FunctionUI.ReadActivity;
import com.example.mental.ModuleUI.CameraModuleActivity;
import com.example.mental.ModuleUI.LookModuleActivity;
import com.example.mental.ModuleUI.TestModuleActivity;
import com.example.mental.R;
import com.example.mental.Utils.ApiRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment implements ModuleAdapter.OnModuleClickListener, FunctionAdapter.OnFunctionClickListener {

    private ViewPager2 viewPager;
    private List<Integer> imageList;
    private TextView textAppName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        initHorizontalRecyclerView(view);
        initBanner(view);
        initFunctionModules(view);
        initActivityList(view);
        initInformationRecyclerView(view);
        initTextAnimation(view);

        return view;
    }


    // 人工智能模块栏初始化
    private void initHorizontalRecyclerView(View view) {
        RecyclerView horizontalRecyclerView = view.findViewById(R.id.horizontalRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalRecyclerView.setLayoutManager(layoutManager);

        List<String> moduleNameList = Arrays.asList("测一测", "照一照", "看一看");
        List<String> moduleIntroduceList = Arrays.asList(
                "只需选择你的想法机器学习就会告诉你答案！",
                "通过你的面部微表情看看你的心情",
                "使用VR技术让你身临其境感受自然放松心情！"
        );

        // 添加 PagerSnapHelper
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(horizontalRecyclerView);

        ModuleAdapter adapter = new ModuleAdapter(moduleNameList, moduleIntroduceList, this);
        horizontalRecyclerView.setAdapter(adapter);
    }

    // 轮播图内容初始化
    private void initBanner(View view) {
        imageList = new ArrayList<>();
        viewPager = view.findViewById(R.id.viewPager);
        BannerAdapter bannerAdapter = new BannerAdapter(imageList);
        viewPager.setAdapter(bannerAdapter);

        String bannerApiUrl = "http://your_api_endpoint_for_banners";
        ApiRequest.makeApiRequest(bannerApiUrl, new ApiRequest.ApiResponseListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(String response) {
                Log.d("API请求内容", response);
                Gson gson = new Gson();
                BannerItem[] bannerItems = gson.fromJson(response, BannerItem[].class);

                imageList.clear();

                for (BannerItem item : bannerItems) {
                    imageList.add(item.getBannerImageResourceId());
                }

                bannerAdapter.notifyDataSetChanged();

                if (!imageList.isEmpty()) {
                    final Handler handler = new Handler();
                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            int currentItem = viewPager.getCurrentItem();
                            viewPager.setCurrentItem((currentItem + 1) % imageList.size(), true);
                            handler.postDelayed(this, 3000);
                        }
                    };
                    handler.postDelayed(runnable, 3000);
                }
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onError(String error) {
                Log.e("API请求内容", error);

                imageList.clear();
                imageList.add(R.drawable.image_banner1);
                imageList.add(R.drawable.image_banner2);
                imageList.add(R.drawable.image_banner3);
                imageList.add(R.drawable.image_banner4);

                bannerAdapter.notifyDataSetChanged();

                if (!imageList.isEmpty()) {
                    final Handler handler = new Handler();
                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            int currentItem = viewPager.getCurrentItem();
                            viewPager.setCurrentItem((currentItem + 1) % imageList.size(), true);
                            handler.postDelayed(this, 3000);
                        }
                    };
                    handler.postDelayed(runnable, 3000);
                }
            }
        });
    }

    // 功能模块栏初始化
    private void initFunctionModules(View view) {
        List<FunctionModule> functionModules = new ArrayList<>();
        functionModules.add(new FunctionModule(R.drawable.icon_function_meditation, "心灵冥想"));
        functionModules.add(new FunctionModule(R.drawable.icon_function_food, "心身滋养"));
        functionModules.add(new FunctionModule(R.drawable.icon_function_note, "心绪记录"));
        functionModules.add(new FunctionModule(R.drawable.icon_function_analyze, "心情解析"));
        functionModules.add(new FunctionModule(R.drawable.icon_function_read, "心理探索"));
        functionModules.add(new FunctionModule(R.drawable.icon_function_game, "逃出困境"));

        RecyclerView functionRecyclerView = view.findViewById(R.id.functionRecyclerView);
        GridLayoutManager functionLayoutManager = new GridLayoutManager(requireContext(), 2);
        functionRecyclerView.setLayoutManager(functionLayoutManager);

        FunctionAdapter functionModuleAdapter = new FunctionAdapter(functionModules, this);
        functionRecyclerView.setAdapter(functionModuleAdapter);
    }

    // 活动列表初始化
    private void initActivityList(View view) {
        final List<ActivityItem> activityList = new ArrayList<>();

        RecyclerView activityRecyclerView = view.findViewById(R.id.activityRecyclerView);
        LinearLayoutManager activityLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        activityRecyclerView.setLayoutManager(activityLayoutManager);

        ActivityAdapter activityAdapter = new ActivityAdapter(activityList);
        activityRecyclerView.setAdapter(activityAdapter);

        String activityURL = "http://jsonplaceholder.typicode.com/post";
        ApiRequest.makeApiRequest(activityURL, new ApiRequest.ApiResponseListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(String response) {
                Log.d("API请求内容", response);
                Gson gson = new Gson();
                ActivityItem[] items = gson.fromJson(response, ActivityItem[].class);

                activityList.addAll(Arrays.asList(items));
                activityAdapter.notifyDataSetChanged();
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onError(String error) {
                Log.e("API请求内容", error);

                ActivityItem[] defaultItems = {
                        new ActivityItem(R.drawable.image_activity_1, "正向心理学实践", "正向心理学实践是一种心理学分支，专注于研究和促进人类幸福、积极情感和个体优势的发展。正向心理学不仅关注减轻心理健康问题，还强调提高生活质量和个体的幸福感。以下是一些正向心理学实践的示例：\n       1. **积极情感练习**：这包括通过培养积极情感，如感激、快乐和满足感，来提高个体幸福感的技巧。人们可以尝试每天记录他们所感到的积极情感，以及导致这些情感的原因。\n       2. **力量和优势的发现**：正向心理学鼓励人们了解他们的个体优势和力量，然后将这些优势应用于他们的生活和工作中。这有助于提高个体的自尊心和自信心。\n      3. **目标设定**：设定明确的目标和愿望，然后制定达到这些目标的计划。积极心理学实践强调将目标与个体的价值和激情相匹配，以增加实现目标的动力。\n        4. **关注流**：\"流\"是一种全神贯注和充实的状态，通常发生在个体在做一项具有挑战性但令人愉悦的任务时。正向心理学鼓励人们追求这种状态，通过选择适合自己兴趣和技能的活动来实现。\n     5. **社交联系**：与他人建立积极和支持性的社交联系对于幸福感至关重要。正向心理学实践强调积极的人际交往和建立亲密关系的重要性。\n        6. **心理弹性的培养**：积极心理学鼓励人们开发心理弹性，以更好地应对生活中的挑战和逆境。这包括积极的问题解决和情感调节技巧。\n     7. **感激实践**：每天记录感激之情，以帮助个体更多地关注积极的方面，减少对负面事物的关注。\n      8. **乐观思考**：培养乐观的思考方式，将注意力集中在积极的可能性和解决方案上，而不是消极的担忧和烦恼上。\n       9. **自我关怀和自我关注**：学会照顾自己的身体和心理健康，包括健康饮食、锻炼、良好的睡眠和冥想等。\n     正向心理学实践的目标是帮助个体建立更加充实、有意义和满足的生活。这些实践可以用于个人生活、职业和教育领域，有助于提高生活的质量和幸福感。正向心理学研究不断发展，为人们提供了更多工具和策略来实现更积极的生活体验。"),
                        new ActivityItem(R.drawable.image_activity_2, "塔罗牌预测心理", "本活动使用机器学习模型训练方法，通过你对塔罗牌内容的选择来预测你的心理方向。\n你對你自己的感覺\nXXI - 世界\n      你感覺你心底的願望快將完美實現。 你對你所得的成就覺得滿意，而且正享受過去努力的回報。在物質財富或精神上，你都處於一段幸福的時期。\n你現在心底最渴望的東西\nXVII - 星星\n        星星卡牌表示，你最渴望的是一個美好和幸福的未來。 如果你生病了，或是在愛情遭遇失望，或是失去了一段關係，你的運氣即將改變。 星星卡牌是你心願卡，它會帶來快樂，滿足和健康的人生。 你還可能會收到禮物。\n        你內心的恐懼\n        VIII - 力量\n        你極度缺乏意志力和力量去對付正在困擾你的某人或某事。 消極的感覺，及恐懼的內心只會造成失敗和失去機會。你應勇敢如獅子，但同時應懷著同情心工作，你將會成功。\n有利於你的事情\n       XV - 惡魔\n      我們見到了有「永久」的可能性。如果你正在考慮確認關係或承諾婚姻等，這是一個好兆頭。 然而，想想你動機，因為我們同時見到了「誘惑」，你們之間存在控制的慾望。 所以，用你的直覺去感受，如果你感受到你的感覺是真誠的，那將是美好的。如果沒有，你仍然有機會作出選擇。 如果正考慮改掉一個壞習慣，例如吸煙或飲酒等，現在是一個好的時機。"),
                        new ActivityItem(R.drawable.image_activity_3, "桌游促进打开心理", "欢迎参加我们的 \"桌游促进打开心理\" 活动！这个活动旨在通过桌面游戏的方式提供一个愉快和互动的环境，帮助参与者打开他们的心理世界，增进社交互动，减轻压力，并增强积极情感。\n活动亮点：\n     桌游乐趣：我们将提供各种精选的桌游，包括策略游戏、卡片游戏、合作游戏等，供您选择。您可以与朋友或新认识的人一起玩游戏，享受游戏的乐趣。\n       社交互动：桌游是一种出色的社交活动，有助于建立新的友谊，改善现有关系，以及提高沟通和协作技能。在游戏中，您可以与他人合作或竞争，共同度过美好时光。\n     心理解压：玩游戏是一种很好的方式来减轻压力和释放紧张情绪。在一个轻松的氛围中，您可以忘记工作和生活的压力，专注于游戏和娱乐。\n        积极情感培养：玩桌游可以激发积极情感，如喜悦、兴奋和满足感。这有助于提高您的情绪状态，增加幸福感。")
                };
                activityList.addAll(Arrays.asList(defaultItems));
                activityAdapter.notifyDataSetChanged();
            }
        });
    }

    // 资讯信息初始化
    private void initInformationRecyclerView(View view) {
        RecyclerView informationRecyclerView = view.findViewById(R.id.recyclerView_home_information);
        GridLayoutManager informationLayoutManager = new GridLayoutManager(requireContext(), 2);
        informationRecyclerView.setLayoutManager(informationLayoutManager);
        List<MessageItem> messageItems = new ArrayList<>();
        messageItems.add(new MessageItem(R.drawable.image_message_1, "AI与心理学完美融合！", "       心理学同人工智能联系紧密，自1956年人工智能的概念提出以来，心理学家同人工智能研究者进行了很多合作研究。如2018年5月，英国《自然》（Nature）杂志刊登了英国伦敦大学神经科学家和英国DeepMind团队人工智能研究员合作完成的一项研究成果，他们利用深度学习技术成功模拟人类大脑的空间导航能力。此类研究向人们展示了人工智能技术在心理学研究中的应用前景。\n      应用于心理测量\n      交互进化计算（Interactive Evolutionary Computation，IEC）属于人工智能领域的一种算法，是一种将人的智能评价同进化计算机有机结合的智能计算方法。目前，交互进化计算在心理测量领域的研究中得到很好的应用。日本学者塔卡西（Hideyuki Takagi）等人将交互进化计算应用于对精神分裂症患者的心理测量和评估中，辅助验证“精神分裂症患者所感受到的情绪表达的动态范围比健康人所感知到的范围更窄”这一假设，该研究是IEC运用于心理测量领域的开创性研究之一。在此之前，精神病学家和心理治疗师认为精神分裂症患者在情感表达方面存在问题，但是由于缺乏定量方法衡量他们的情感表达能力，所以无法以此作为诊断依据。交互进化计算提供了一种定量的测量方法，使得对情绪感知范围的测量成为可能。之后，张琰等人利用交互进化计算技术，以高社交焦虑和低社交焦虑大学生为研究对象，成功地测量并比较了两者在面孔情绪识别的动态感知范围上的差异性。这些研究表明：交互进化计算作为一种智能算法，适用于心理健康测量。\n     此外，人工智能领域的贝叶斯网络和粗糙集分析方法对心理测量数据的挖掘起到了优于一般心理学统计方法的作用。余嘉元发现，利用贝叶斯网络开发的智能自适应测验可以显著地减少教育和心理测试中题目的数量，并且相对于纸笔测验，这种自适应测验获取的信息更多。他还发现，人工智能中的粗糙集分析方法可以对心理测量数据进行挖掘，得到更准确细致的分析结果。\n        应用于心理变量预测\n     近年来，人工智能技术中的表情识别技术被用于心理学人格预测的研究中。以往确定大五人格类型的方法主要是问卷测量，但这需要花费大量时间。加夫里列斯库（Mihai Gavrilescu）在2016年建立了一种新的非侵入性系统，这一系统可以根据面部动作编码获得的面部特征来确定人的大五人格特征。之后，加夫里列斯库和维齐雷努（Nicolae Vizireanu）在2017年提出了一种基于面部动作编码系统的面部特征分析系统，用以预测人们的16PF人格特征。该系统能够在1分钟内准确预测个体的16PF人格，比16PF人格问卷更快速、更实用，适合于短时间内预测人的个性特征。", "2023年8月22日", "腾讯云"));
        messageItems.add(new MessageItem(R.drawable.image_message_2, "善用科技治疗文明病！研究：AI 心理治疗可改变大脑功能，改善忧鬱及焦虑症状", "     由伊利诺伊大学芝加哥分校学者执行的研究，招募了 60 多名患者进行临床研究，评估 Lumen 应用程式（亚马逊 Alexa 应用程式中的一项技能）对轻度至中度忧鬱和焦虑症状的效果。参与者平均年龄为 37.8 岁，其中 68% 为女性、25% 为黑人、24% 为拉丁裔、11% 为亚洲人。其中有 42 人 在 iPad 上使用 Lumen 进行 8 次问题解决治疗，21 人则作为对照组，未接受任何干预措施。\n     研究结果发现，与对照组相较，使用 Lumen 应用程式的参与者在忧鬱、焦虑和心理困扰方面的得分降低，还显示出其解决问题能力的提升，这与背外侧前额叶（认知控制有关的大脑区域）活动的增加有关。\n        研究者表示，此种治疗方式可以帮助人们改变对问题的看法和应对方式，不受到情绪的干扰，同时秉持「实用」和「以患者为中心」的原则，且已经过实证可以透过语音技术来传递和运用。\n       台北市联合医院中兴院区一般精神科主任詹佳真说明，本研究把治疗后情绪改善、行为上问题解决技巧的提升，以及生物学上主掌理性思考的脑区：背外侧前额叶活性增加做为连结，证明 AI 心理治疗確实可改变大脑功能活动，这是令人振奋的消息。\n      心理治疗的核心概念为：说出来就能得到帮忙，所以只要能协助开启谈话，对於被焦虑忧鬱情绪淹没的人就是一种帮助。本研究帮助受治疗者从情绪中抽离，聚焦在產生困扰的事件上，结合了「寻找解决问题的方法」与「產生对於生活的掌控感及自信心」这两项有效的治疗因子，因此可预见，即使是 AI 执行，也不容易误导案主，且能达到一定疗效。\n     不过詹佳真提醒，虽然 AI 可弥补现实中无法即刻安排心理治疗的需求，但人类的问题错综复杂，仍需要不同取向的心理治疗来协助解救情绪方面的问题。AI 目前仍然无法替代面对面的真人心理治疗，且本研究並非针对已经確诊焦虑症或忧鬱症的个人，因此，无法从心理治疗得到帮助的人还是务必及时寻求协助与就医，经过正確的诊断与遵照医嘱接受治疗，才能有良好的治疗效果。\n     董氏基金会心理卫生中心主任叶雅馨说明，在新冠病毒大流行之后，焦虑和忧鬱的发生率上升，当心理治疗专业人员不足时，人工智慧语音就像一位虚擬教练，可作为填补心理健康系统迫切需求的一种方法。这不代表人工智慧语音可以取代传统治疗，但可以是人们寻求治疗、等待治疗前的权宜措施。\n      许多民眾对使用人工智慧十分陌生，不知道该不该尝试？叶雅馨表示，面对人工智慧语音的治疗方式，建议大眾可以先站在「何妨试一试、愿意试一试」的角度，尝试之前也可蒐集关於人工智慧相关的资料，或询问使用者的反馈，帮助自己更了解该领域。最重要的是，无论哪个治疗方法，对迫切需要的人来说都是一个选择，也许在尝试过会得到新的想法及帮助。", "2023年8月22日", "HEHO"));
        messageItems.add(new MessageItem(R.drawable.image_message_3, "当AI侵入心理咨询，科技何以拯救心灵？", "在1960年代，麻省理工学院的计算机科学家约瑟夫·魏岑鲍姆（Joseph Weizenbaum）开发了一个名为伊丽莎（Eliza）的计算机程序。它旨在模拟罗杰斯心理治疗，在这种疗法中，通常由患者主导对话，而治疗师通常会重复她的措辞。\n      罗杰斯心理治疗，又称为“个人中心疗法”（person centered therapy），治疗过程中为来访者提供和谐适当的心理环境和氛围，与来访者建立一种相互信任、相互接受的关系，帮助来访者发挥潜能，理清思路，改变对自己和他人的看法，产生自我指导的行为，达到自我治疗、自我成长的目的。\n        魏岑鲍姆把伊丽莎视为一种讽刺。他对计算机是否能够模拟有意义的人际互动表示怀疑。但当许多尝试这款程序的人发现伊丽莎既有实际效用又饱含吸引力时，他感到震惊。他个人秘书甚至要求他离开房间，这样她就可以独自与伊丽莎相处。更糟糕的是，医生们将其视为一种潜在的变革性工具。\n        1966年，三名精神科医生在《神经与精神疾病杂志》（The Journal of Nervous and Mental Disease）中如是写道，“一个为此目的而设计的计算机系统，每小时可以处理数百位患者。参与该系统的设计和运作的人类治疗师，并不会被系统取代，但会更加高效，因为他的精力不再局限于现有的患者—治疗师一对一。”\n       魏岑鲍姆成为了人工智能坦率的批评者。布莱恩·克里斯汀（Brian Christian）在他的书《最人性化的人》（The Most Human Human）中详细描述了这一事件，他告诉我说：“精灵已经从瓶子里跑出来了。”\n     几年后，斯坦福大学的精神科医生肯尼斯·科尔比（Kenneth Colby）创建了“帕里”（Parry），一个试图模拟偏执型精神分裂症患者语言的程序，以便在学生照顾真实患者之前对他们预先培训。精神科医生在拿到治疗记录后，往往无法分辨帕里和人类之间的区别；狭义上讲，这个聊天机器人已经通过了图灵测试。在1972年，帕里和伊丽莎见面进行了一次治疗会话：\n     随着时间的推移，程序员们开发了Jabberwacky、Dr. Sbaitso和Alice（人工语言互联网计算机实体）。与这些聊天机器人的交流往往是引人入胜的，有时滑稽可笑，偶尔也会言之无物。但计算机能够扮演人类的知己角色，超越疲于奔命的治疗师的能力极限，扩大治疗的覆盖范围——这样的想法历经数十年而坚存。\n      2017年，斯坦福大学的临床研究心理学家艾莉森·达西（Alison Darcy）创立了Woebot，这是一家通过智能手机应用提供自动化心理健康支持的公司。其方法基于认知行为疗法（Cognitive Behavioral Therapy，CBT），这种治疗旨在改变人们的思维模式。该应用程序使用一种称为自然语言处理的人工智能形式来解释用户说的话，并通过预先编写的回答序列引导用户，促使他们考虑自己的思维方式可能如何不同。", "2023年6月11日", "虎嗅"));
        messageItems.add(new MessageItem(R.drawable.image_message_4, "被忽略的「AI心理學」", "       關於我們的生活如何與AI產生交集，有個仍被忽視的問題，就是人類的自我認同感會受到這類科技的影響。想想你若接到夢想工作的錄取通知，卻得知是AI進行審核而非真人主管，是否會認為自己其實沒有這麼優秀？這樣的心理反應帶來什麼樣的影響？\n     上過行銷課的人，可能記得通用磨坊公司（General Mills）1950年代推出貝蒂妙廚（Betty Crocker）蛋糕粉的著名案例。這種蛋糕粉只需加入水，攪拌，然後烘焙即可。儘管這款產品表現優異，一開始的銷售情況卻不如人意。大家百思不解，直到主管找出問題的癥結：蛋糕粉把烤蛋糕變得太容易了，讓購買的人在使用的時候感覺像在作弊。基於這個見解，通用磨坊把成分中的雞蛋粉拿掉，要顧客自己打一顆雞蛋，拌入蛋糕粉。這麼一個小小的改變，讓那些烘焙的人覺得比較心安理得，因此提高了蛋糕粉的銷售。事隔70年，至今大部分蛋糕粉仍要使用者自己加入一顆雞蛋。", "2023年9月1日", "哈佛商业评论"));
        messageItems.add(new MessageItem(R.drawable.image_message_5, "AI 可以取代心理治療嗎？一位心理師與 ChatGpt 的深夜對話...", "      你聽過 AI 嗎？昨晚下班後，決定與名為 ChatGpt 的 AI 進行一場對話。原因很簡單：有人說，ChatGpt 可以寫詩，可以發Email，還可以即興唱一首主題為「椅子」的日文歌。\n     那麼，AI 可以取代心理治療嗎？\n      這，就是這場對話的動機。\n      我覺得目前民用的AI，有點像是以前台中一中街的刀削麵。因為生意太好了，老闆弄了一台機器人，專門把麵條削好。意思是：機器人就是負責「削」，削好了還是要由人工來檢查、來煮熟、來調味。\n     目前我用 ChatGPT-4，也是類似的感想。\n       先不管字數限制的問題。對我來說最好用的功能如翻譯、整理段落等。一旦一次給太多資料（麵條），AI就會出包。更糟糕的是：\n1. 因為你一次給很多資料。就人性而言，會更懶得檢查。\n2. ChatGpt就算是4，還是會有「一本正經說幹話」的毛病。\n3. 信任難以建立，容易摧毀，只要抓到AI一次說謊，那就...\n        所以人類那種「完蛋了！以後什麼東西丟給AI，它就可以無止盡地工作，取代我們每個人」的幻想，至少目前還是沒有成立。\n      就算有了削麵機器人，我們還是需要煮麵師傅。就算有了無盡的文字產出，我們還是需要編輯。\n1. 能夠分辨真貨與假貨、好貨與爛貨的人，永遠是未來需要的人。\n2. 這也意味著「專家效度」的微妙邏輯，什麼是夠好的內容？讓我們來訪問一群專家吧。\n3. 少了對AI的全面信任（或許是好事），我目前的解法是：一次只給AI一小段。", "2023年9月6日", "家齊心理師說故事"));
        messageItems.add(new MessageItem(R.drawable.image_message_6, "和機器人聊心事？歐美AI心理諮商熱潮飆升中", "和機器人聊心事？歐美AI心理諮商熱潮飆升中", "2023年9月13日", "天下杂志"));
        messageItems.add(new MessageItem(R.drawable.image_message_7, "AI 入局心理健康：是抢咨询师的饭碗，还是治愈 emo 的良药？", "AI 入局心理健康：是抢咨询师的饭碗，还是治愈 emo 的良药？", "2023年7月22日", "InfoQ"));
        messageItems.add(new MessageItem(R.drawable.image_message_8, "AI在心理学研究与相关产业的应用", "AI在心理學研究與相關產業的應用", "2023年8月1日", "科学人"));

        MessageAdapter messageAdapter = new MessageAdapter(messageItems, requireContext());
        informationRecyclerView.setAdapter(messageAdapter);
    }

    // 初始化文本大小切换动画
    private void initTextAnimation(View view) {
        textAppName = view.findViewById(R.id.text_fragment_home_appName); // 初始化 textAppName
        // 加载动画资源
        Animation textAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.text_size_animation);
        // 设置动画监听器，在动画结束时反转动画
        textAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // 动画开始时的操作
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // 动画重复时的操作
            }
        });
        // 设置动画的重复次数为无限循环
        textAnimation.setRepeatCount(Animation.INFINITE);

        // 开始动画
        textAppName.startAnimation(textAnimation);
    }


    // 人工智能模块点击跳转事件
    @Override
    public void onModuleClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(requireContext(), TestModuleActivity.class));
                break;
            case 1:
                startActivity(new Intent(requireContext(), CameraModuleActivity.class));
                break;
            case 2:
                startActivity(new Intent(requireContext(), LookModuleActivity.class));
                break;
        }
    }

    // 功能模块点击跳转事件
    @Override
    public void onFunctionClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(requireContext(), MeditationActivity.class));
                break;
            case 1:
                startActivity(new Intent(requireContext(), FoodActivity.class));
                break;
            case 2:
                startActivity(new Intent(requireContext(), NoteActivity.class));
                break;
            case 3:
                startActivity(new Intent(requireContext(), AnalyzeActivity.class));
                break;
            case 4:
                startActivity(new Intent(requireContext(), ReadActivity.class));
                break;
            case 5:
                startActivity(new Intent(requireContext(), GameActivity.class));
                break;
        }
    }
}
