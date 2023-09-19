package com.example.mental.FunctionUI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.BookAdapter;
import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.Definition.BookItem;
import com.example.mental.R;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        // 初始化头部标签
        RecyclerView headerRecyclerView = findViewById(R.id.ReadHeaderRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        String headerText = "心里探索";
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);

        // 设置书库组: 心理学问
        RecyclerView recyclerView_read_psychology = findViewById(R.id.recyclerView_read_psychology);
        LinearLayoutManager psychologyLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_read_psychology.setLayoutManager(psychologyLayoutManager);
        PagerSnapHelper psychologyPagerSnapHelper = new PagerSnapHelper();
        psychologyPagerSnapHelper.attachToRecyclerView(recyclerView_read_psychology);
        List<BookItem> psychologyHealthBookItems = new ArrayList<>();
        // 为心理学问书目添加数据并Adapter进行数据渲染配置
        psychologyHealthBookItems.add(new BookItem(R.drawable.image_book_socialpsychology, "《社会心理学》", "社会心理学"));
        psychologyHealthBookItems.add(new BookItem(R.drawable.image_book_psychologyandlife, "《心理学与生活》", "       《心理学与生活》是美国斯坦福大学多年来使用的教材，也是在美国许多大学里推广使用的经典教材，被ETS推荐为GRE心理学专项考试的主要参考用书，还是被许多国家大学的“普通心理学”课程选用的教材。\n       这本教科书写作流畅，通俗易懂，深入生活，把心理学理论与知识联系人们的日常生活与工作，使它同样也成为一般大众了解心理学与自己的极好读物。\n       作为一本包含着丰富的教育思想和独特教学方法的成熟教材，原书中所有元素——如由600余条词汇及解释组成的“专业术语表”，2000余条“参考文献”，以及近1000条的“人名和主题索引”等等，对于教学、研究和学习都十分宝贵，此中译本完整地翻译和保留了这些资料。"));
        psychologyHealthBookItems.add(new BookItem(R.drawable.image_book_thepsychologyofprocrastination, "《拖延心理学》", "       从学生到科学家，从秘书到总裁，从家庭主妇到销售员，拖延的问题几乎会影响到每一个人。本书的两位作者基于他们倍受好评和极具开创性的拖延工作坊和从众多心理咨询领域中汲取的丰富理论和经验，对拖延作了一次仔细、详尽、有时也颇为幽默的探索。\n       通过鉴别和检查那些我们将事情推掉的背后原因——对失败、成功、控制、疏远和依附的恐惧，加上我们的时间概念问题和大脑的神经学因素——为我们学会怎样理解拖延的冲动以及怎样以全新方式采取行动做了一件非常扎实的基础工作。\n     作者为我们提供了达成目标、管理时间、谋求支持和处理压力等一系列方案来克服拖延问题，她们提供的方案极为实用并经受过实践的检验。本书还考虑到工作和生活节奏不断加快的当代文化诉求，以及诸如注意力缺失紊乱症、执行功能障碍症等神经认知问题对拖延的影响。本书甚至还为生活和工作在拖延者身边的人群提供了不少实用性建议。"));
        psychologyHealthBookItems.add(new BookItem(R.drawable.image_book_psychologyrulestheworld, "《心理学统治世界》", ""));
        psychologyHealthBookItems.add(new BookItem(R.drawable.image_book, "111", "书名3"));
        BookAdapter psychologyAdapter = new BookAdapter(this, psychologyHealthBookItems);
        recyclerView_read_psychology.setAdapter(psychologyAdapter);


        // 设置书库组: 人工智能
        RecyclerView recyclerView_read_AI = findViewById(R.id.recyclerView_read_AI);
        LinearLayoutManager AILayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_read_AI.setLayoutManager(AILayoutManager);
        PagerSnapHelper AIPagerSnapHelper = new PagerSnapHelper();
        AIPagerSnapHelper.attachToRecyclerView(recyclerView_read_AI);
        List<BookItem> AIBookItems = new ArrayList<>();
        AIBookItems.add(new BookItem(R.drawable.image_book, "111", "书名4"));
        AIBookItems.add(new BookItem(R.drawable.image_book, "111", "书名5"));
        AIBookItems.add(new BookItem(R.drawable.image_book, "111", "书名5"));
        AIBookItems.add(new BookItem(R.drawable.image_book, "111", "书名5"));
        AIBookItems.add(new BookItem(R.drawable.image_book, "111", "书名5"));
        AIBookItems.add(new BookItem(R.drawable.image_book, "111", "书名5"));
        AIBookItems.add(new BookItem(R.drawable.image_book, "111", "书名5"));
        AIBookItems.add(new BookItem(R.drawable.image_book, "111", "书名5"));
        AIBookItems.add(new BookItem(R.drawable.image_book, "111", "书名6"));
        BookAdapter aiAnalyzeAdapter = new BookAdapter(this, AIBookItems);
        recyclerView_read_AI.setAdapter(aiAnalyzeAdapter);

        // 设置书库组: 中外历史
        RecyclerView recyclerView_read_history = findViewById(R.id.recyclerView_read_history);
        LinearLayoutManager historyLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_read_history.setLayoutManager(historyLayoutManager);
        PagerSnapHelper historyPagerSnapHelper = new PagerSnapHelper();
        historyPagerSnapHelper.attachToRecyclerView(recyclerView_read_history);
        List<BookItem> historyBookItems = new ArrayList<>();
        historyBookItems.add(new BookItem(R.drawable.image_book, "111", "书名7"));
        historyBookItems.add(new BookItem(R.drawable.image_book, "111", "书名8"));
        historyBookItems.add(new BookItem(R.drawable.image_book, "111", "书名8"));
        historyBookItems.add(new BookItem(R.drawable.image_book, "111", "书名8"));
        historyBookItems.add(new BookItem(R.drawable.image_book, "111", "书名8"));
        historyBookItems.add(new BookItem(R.drawable.image_book, "111", "书名8"));
        historyBookItems.add(new BookItem(R.drawable.image_book, "111", "书名8"));
        historyBookItems.add(new BookItem(R.drawable.image_book, "111", "书名8"));
        historyBookItems.add(new BookItem(R.drawable.image_book, "111", "书名9"));
        BookAdapter historyAdapter = new BookAdapter(this, historyBookItems);
        recyclerView_read_history.setAdapter(historyAdapter);

        // 设置书库组: 中外历史
        RecyclerView recyclerView_read_classics = findViewById(R.id.recyclerView_read_classics);
        LinearLayoutManager classicsLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_read_classics.setLayoutManager(classicsLayoutManager);
        PagerSnapHelper classicsPagerSnapHelper = new PagerSnapHelper();
        classicsPagerSnapHelper.attachToRecyclerView(recyclerView_read_classics);
        List<BookItem> classicsBookItems = new ArrayList<>();
        classicsBookItems.add(new BookItem(R.drawable.image_book_abriefhistoryofmankind, "《人类简史》", "        在7万年前，智人还不过是一种微不足道的动物，在非洲的角落自顾自地生活。但就在接下来的几千年间，智人就成了整个地球的主人、生态系统的梦魇。\n      时至今日，智人似乎只要再跨一步就能进入神的境界，不仅有望获得永恒的青春，更拥有创造和毁灭一切的神力。 \n       但遗憾的是，智人在地球上的所作所为，实在没有太多令人自豪。虽然我们主宰了环境、增加了粮食产量、盖起城市、建立帝国，还创造了无远弗届的贸易网络，但全球的痛苦减少了吗？一次又一次，虽然整体人类的能力大幅提升，但却不一定能改善个别人类的福祉，而且常常还让其他动物深受其害。 \n        在过去的几十年间，至少就人类的生存条件而言有了确实的进步，饥荒、瘟疫和战争都已减少。然而，其他动物的生存条件却是以前所未有的速度急遽恶化，而且就算是人类相关的改进，也还需要再长时间观察才能判断是否利大于弊，是否能够延续。 \n       此外，虽然现在人类已经拥有许多令人赞叹的能力，但我们仍然对目标感到茫然，而且似乎也仍然总是感到不满。我们的交通工具已经从独木舟变成帆船、变成汽船、变成飞机，再变成航天飞机，但我们还是不知道自己该前往的目的地。我们拥有的力量比以往任何时候都更强大，但几乎不知道该怎么使用这些力量。更糟糕的是，人类似乎也比以往任何时候更不负责。我们让自己变成了神，而唯一剩下的只有物理法则，我们也不用对任何人负责。正因如此，我们对周遭的动物和生态系统掀起一场灾难，只为了寻求自己的舒适和娱乐，但从来无法得到真正的满足。 \n        拥有神的能力，但是不负责任、贪得无厌，而且连想要什么都不知道。天下危险，恐怕莫此为甚。"));
        classicsBookItems.add(new BookItem(R.drawable.image_book_waitingforbarbarians, "《等待野蛮人》", "      我从未见过这样的东西：两个圆圆的小玻璃片架在他眼睛前的环形金属丝上。他是瞎子吗？如果他是个盲人想要掩饰这一点，我倒可以理解。但他并不瞎。\n     那小圆玻璃片是暗色的，从里面看出来并不透明，但他就是能透过这样的玻璃片看过来。他告诉我，这是一种新发明的玩意儿：它能保护眼睛，不受阳光的炫照，戴上它就不必成天眯缝着眼。也可减少头痛。瞧—— 他轻轻触一下自己的眼角，不长皱纹。他重又架回那一对玻璃片。这倒不假，看皮肤他真比他的岁数显得年轻多了。在我们那里，人人都戴这玩意儿。\n        我们坐在旅馆最好的房间里，我和他之间隔着一个长颈瓶，还有一盆坚果。彼此都没有提及他此行的目的，他来这儿肯定是出于事情的某种紧迫性，明白这一点就够了。我们只是谈些打猎的事儿。他说起前不久一次驱车大狩猎的经历，当时成百上千的鹿、猪和熊被杀死，漫山遍野都是动物尸体，多得没法收拾，只好让它们去烂掉（那真是罪过）。我告诉他每年都有成群的野鹅和野鸭迁徙到这儿的湖里，当地人怎样设陷阱去捕捉。"));
        classicsBookItems.add(new BookItem(R.drawable.image_book, "111", "书名8"));
        classicsBookItems.add(new BookItem(R.drawable.image_book, "111", "书名8"));
        classicsBookItems.add(new BookItem(R.drawable.image_book, "111", "书名8"));
        classicsBookItems.add(new BookItem(R.drawable.image_book, "111", "书名8"));
        classicsBookItems.add(new BookItem(R.drawable.image_book, "111", "书名8"));
        classicsBookItems.add(new BookItem(R.drawable.image_book, "111", "书名8"));
        classicsBookItems.add(new BookItem(R.drawable.image_book, "111", "书名8"));
        classicsBookItems.add(new BookItem(R.drawable.image_book, "111", "书名9"));
        BookAdapter classicsAdapter = new BookAdapter(this, classicsBookItems);
        recyclerView_read_classics.setAdapter(classicsAdapter);

    }
}