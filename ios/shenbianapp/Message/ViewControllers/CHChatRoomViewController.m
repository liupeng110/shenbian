//
//  CHChatRoomViewController.m
//  shenbianapp
//
//  Created by book on 2017/10/17.
//  Copyright © 2017 . All rights reserved.
//

#import "CHChatRoomViewController.h"

#import <RongIMKit/RongIMKit.h>
#import <JrmfWalletKit/JrmfWalletKit.h>
#import "CHChatLocationViewController.h"


@interface CHChatRoomViewController ()<RCLocationPickerViewControllerDelegate,RCLocationPickerViewControllerDataSource>

@end

@implementation CHChatRoomViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.

    self.navigationController.navigationBarHidden = NO;
    self.tabBarController.tabBar.hidden = YES;


    UIBarButtonItem *backItem = [[UIBarButtonItem alloc]initWithImage:[UIImage imageNamed:@"tx_fh"] landscapeImagePhone:[UIImage imageNamed:@"tx_fh"] style:(UIBarButtonItemStyleDone) target:self action:@selector(clickBackButton)];
    self.navigationItem.leftBarButtonItem  = backItem;
    self.navigationController.navigationBar.tintColor = [UIColor whiteColor];

    
}

-(void)clickBackButton{
    [self.navigationController popViewControllerAnimated:YES];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.tabBarController.tabBar.hidden = YES;

}
-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    self.tabBarController.tabBar.hidden = NO;

}
/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/


-(void)pluginBoardView:(RCPluginBoardView *)pluginBoardView clickedItemWithTag:(NSInteger)tag{
    ;
    if (tag==1003) {
       CHChatLocationViewController   *locationVC = [[CHChatLocationViewController alloc]init];
        locationVC.delegate = self;
        [self.navigationController pushViewController:locationVC animated:YES];
    } else if (tag == 1004){
        [JrmfWalletSDK openWallet];
    } else {
        [super pluginBoardView:pluginBoardView clickedItemWithTag:tag];
    }

}


-(void)locationPicker:(RCLocationPickerViewController *)locationPicker didSelectLocation:(CLLocationCoordinate2D)location locationName:(NSString *)locationName mapScreenShot:(UIImage *)mapScreenShot{

    RCLocationMessage *locationMessage = [RCLocationMessage messageWithLocationImage:mapScreenShot location:location locationName:locationName];
    [self sendMessage:locationMessage pushContent:nil];
    [self.navigationController popViewControllerAnimated:YES];

}



@end
