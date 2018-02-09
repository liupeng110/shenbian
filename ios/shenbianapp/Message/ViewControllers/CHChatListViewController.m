//
//  CHChatListViewController.m
//  shenbianapp
//
//  Created by Hero on 2018/2/9.
//  Copyright © 2018年 helinkeji. All rights reserved.
//

#import "CHChatListViewController.h"
#import "CHChatRoomViewController.h"
@interface CHChatListViewController ()<RCIMUserInfoDataSource>

@end

@implementation CHChatListViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.navigationController.navigationBarHidden = NO;
    self.tabBarController.tabBar.hidden = YES;
    self.title = @"会话列表";
    UIBarButtonItem *backItem = [[UIBarButtonItem alloc]initWithImage:[UIImage imageNamed:@"tx_fh"] landscapeImagePhone:[UIImage imageNamed:@"tx_fh"] style:(UIBarButtonItemStyleDone) target:self action:@selector(clickBackButton)];
    self.navigationItem.leftBarButtonItem  = backItem;
    //设置需要显示哪些类型的会话
    [self setDisplayConversationTypes:@[@(ConversationType_PRIVATE),
                                        @(ConversationType_DISCUSSION),
                                        @(ConversationType_CHATROOM),
                                        @(ConversationType_GROUP),
                                        @(ConversationType_APPSERVICE),
                                        @(ConversationType_SYSTEM)]];
    //设置需要将哪些类型的会话在会话列表中聚合显示
    [self setCollectionConversationType:@[@(ConversationType_DISCUSSION),
                                          @(ConversationType_GROUP)]];
    
    [RCIM sharedRCIM].userInfoDataSource = self;
    
    self.conversationListTableView.tableFooterView = [UIView new];
}

- (void)onSelectedTableRow:(RCConversationModelType)conversationModelType
         conversationModel:(RCConversationModel *)model
               atIndexPath:(NSIndexPath *)indexPath {
    CHChatRoomViewController *conversationVC = [[CHChatRoomViewController alloc]init];
    conversationVC.conversationType = model.conversationType;
    conversationVC.targetId = model.targetId;
    conversationVC.title = model.conversationTitle;
    [self.navigationController pushViewController:conversationVC animated:YES];
}

-(void)clickBackButton{
    [self.navigationController popViewControllerAnimated:YES];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

- (void)getUserInfoWithUserId:(NSString *)userId completion:(void (^)(RCUserInfo *))completion {
    NSLog(@"userId:%@",userId);
    
}



@end
