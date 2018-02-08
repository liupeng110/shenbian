//
//  CHMessageViewController.m
//  shenbianapp
//
//  Created by book on 2017/10/17.
//  Copyright © 2017 . All rights reserved.
//

#import "CHMessageViewController.h"
#import "CHMessageTableViewCell.h"
#import "CHChatRoomViewController.h"

#import "CHFocusViewController.h"
#import "CHMyOrdersViewController.h"
@interface CHMessageViewController ()<UITableViewDelegate,UITableViewDataSource,UIScrollViewDelegate,RCIMReceiveMessageDelegate>

@property(nonatomic,strong)UIView *upperView;
@property(nonatomic,strong)UIView* lowerView;
@property(nonatomic,strong)UIScrollView *scrollView;
@property(nonatomic,strong)UIPageControl *pageControl;

@property(nonatomic,strong)UITableView *tableView;

@end

@implementation CHMessageViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.navBarView.mhBaseTitleLabel.text = @"消息";
    self.navigationController.navigationBar.tintColor = [UIColor colorWithHexColor:@"#008E8F"];
    [self.view addSubview:self.upperView];
    [self.upperView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.view).offset(64);
        make.left.right.equalTo(self.view);
        make.height.mas_equalTo(230);
    }];
    
    [self.upperView addSubview:self.scrollView];
    [self.scrollView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.edges.equalTo(self.upperView);
    }];
    
    [self.upperView addSubview:self.pageControl];
    [self.pageControl mas_makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.equalTo(self.upperView);
        make.bottom.equalTo(self.upperView.mas_bottom);
    }];
    
    for (int i = 0;  i < 3; i++) {
        UIImageView *imageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"sy_sj_cover"]];

        [self.scrollView addSubview:imageView];
        [imageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.edges.equalTo(self.upperView);
        }];
    }
    self.scrollView.contentSize = CGSizeMake(kScreenWidth * 5, 200);
    
    [self.view addSubview:self.tableView];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(self.view);
        make.top.equalTo(self.upperView.mas_bottom);
        make.bottom.equalTo(self.view).offset(-49);
    }];
    
    NSMutableArray *tempArr = [NSMutableArray array];
    for (int i = 0; i < 3; i++) {
        CHMessageModel *model = [[CHMessageModel alloc]init];
        model.headUrl = @"";
        model.userName = @"用户";
        model.briefMessage = @"简要消息";
        model.lastTime = [NSString stringWithFormat:@"昨天 %d：%d",13 + i,20 - i];
        model.messageType = i;
        [tempArr addObject:model];
    }
    self.messageModelList = tempArr;
    
//    [RCIM sharedRCIM].userInfoDataSource = self;
    
//    [[RCIMClient sharedRCIMClient] setReceiveMessageDelegate:self object:nil];
//    [RCIMClient sharedRCIMClient].enableMessageAttachUserInfo = YES;
    [[RCIM sharedRCIM] setReceiveMessageDelegate:self];

}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.tabBarController.tabBar.hidden = NO;
    self.navigationController.navigationBarHidden = YES;
    
    NSArray *conversationList = [[RCIMClient sharedRCIMClient]
                                 getConversationList:@[@(ConversationType_PRIVATE),
                                                       @(ConversationType_DISCUSSION),
                                                       @(ConversationType_GROUP),
                                                       @(ConversationType_SYSTEM),
                                                       @(ConversationType_APPSERVICE),
                                                       @(ConversationType_PUBLICSERVICE)]];
    for (RCConversation *conversation in conversationList) {
        NSLog(@"会话类型：%@，目标会话ID：%@", conversation.conversationTitle, conversation.targetId);
    }
    if (conversationList.count > 0) {
        RCConversation *conversation = conversationList.lastObject;
        
    }
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(UIView *)upperView{
    
    if (_upperView == nil) {
        _upperView = [[UIView alloc]init];
        _upperView.userInteractionEnabled = YES;
        _upperView.backgroundColor = [UIColor redColor];
    }
    return _upperView;
}

-(UIScrollView *)scrollView{
    
    if (_scrollView == nil) {
        _scrollView = [UIScrollView new];
        _scrollView.pagingEnabled = YES;
        _scrollView.showsVerticalScrollIndicator = NO;
        _scrollView.showsHorizontalScrollIndicator = NO;
        _scrollView.delegate = self;
        _scrollView.backgroundColor =[UIColor orangeColor];
    }
    return _scrollView;
}

-(UIPageControl *)pageControl{
    if (_pageControl == nil) {
        _pageControl = [[UIPageControl alloc]init];
        _pageControl.numberOfPages = 5;
        _pageControl.centerX = kScreenWidth/4;
        _pageControl.centerY = 160;
    }
    return _pageControl;
}

#pragma -mark scrollView delegate.

-(void)scrollViewDidScroll:(UIScrollView *)scrollView{
    CGPoint point = scrollView.contentOffset;
    int count =  point.x / kScreenWidth;
    self.pageControl.currentPage = count;
}

#pragma -mark tableview 

-(UITableView *)tableView{
    
    if (_tableView == nil) {
        _tableView = [[UITableView alloc]init];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        [_tableView registerClass:[CHMessageTableViewCell class] forCellReuseIdentifier:@"messageCell"];
        _tableView.tableFooterView = [[UIView alloc]init];
    }
    return _tableView;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    CHMessageTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"messageCell"];
    cell.model = self.messageModelList[indexPath.row];
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    
    return self.messageModelList.count;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    return 80;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    [tableView deselectRowAtIndexPath:indexPath animated:true];
    
    CHMessageModel *model = self.messageModelList[indexPath.row];
    
    switch (model.messageType) {
        case MessageTypeChat:{
            NSUserDefaults *ud = [NSUserDefaults standardUserDefaults];
            NSString *im_userId = [ud objectForKey:@"im_userId"];
            if (im_userId) {                
                CHChatRoomViewController *chatRoom = [[CHChatRoomViewController alloc]initWithConversationType:ConversationType_PRIVATE targetId:im_userId];
                [self.navigationController pushViewController:chatRoom animated:YES];
            } else {
                UIAlertView *alertView = [[UIAlertView alloc]initWithTitle:@"温馨提示" message:@"暂时还没有新消息哦" delegate:nil cancelButtonTitle:@"知晓" otherButtonTitles: nil];
                [alertView show];
            }
        }
            
            break;
        case MessageTypeOrder:{//跳转订单详情
            CHMyOrdersViewController *myOrder = [CHMyOrdersViewController new];
            [self.navigationController pushViewController:myOrder animated:YES];
        }
            break;
            
        case MessageTypeFocus:{//跳转关注页
            
            CHFocusViewController *focus = [CHFocusViewController new];
            [self.navigationController pushViewController:focus animated:YES];
        }
            
            break;
        default:
            break;
    }
}



-(void)onRCIMReceiveMessage:(RCMessage *)message left:(int)left{
   
    if ([message.content isMemberOfClass:[RCTextMessage class]]) {
        RCTextMessage *testMessage = (RCTextMessage *)message.content;
        NSLog(@"消息内容：%@", testMessage.content);
        dispatch_async(dispatch_get_main_queue(), ^{
            NSIndexPath *indexPath = [NSIndexPath indexPathForRow:0 inSection:0];
            CHMessageTableViewCell *cell = [self.tableView cellForRowAtIndexPath:indexPath];
            cell.briefMsgLabel.text = testMessage.content;
            if (message.content.senderUserInfo) {
                cell.userNameLabel.text = testMessage.senderUserInfo.name;
                [cell.headImage setImageURL:[NSURL URLWithString:testMessage.senderUserInfo.portraitUri]];
            }
            
            NSDate *sendDate = [NSDate dateWithTimeIntervalSince1970:message.sentTime/1000];
            NSDateFormatter *formater = [NSDateFormatter new];
            [formater setDateFormat:@"MM-dd HH:mm:ss"];
            
            cell.lastTimeLabel.text = [formater stringFromDate:sendDate];
            NSUserDefaults *ud  = [NSUserDefaults standardUserDefaults];
            [ud setObject:message.targetId forKey:@"im_userId"];
            [ud setObject:testMessage.senderUserInfo.name forKey:@"im_userName"];
            [ud synchronize];
            
        });
    }

    
}

@end
