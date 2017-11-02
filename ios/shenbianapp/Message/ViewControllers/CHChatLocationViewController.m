//
//  CHChatLocationViewController.m
//  shenbianapp
//
//  Created by book on 2017/10/29.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHChatLocationViewController.h"

@interface CHChatLocationViewController ()

@end

@implementation CHChatLocationViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.

    UIBarButtonItem *leftItem = self.navigationItem.leftBarButtonItem;
    [leftItem setTintColor:[UIColor blackColor]];

    UIBarButtonItem *rightItem = self.navigationItem.rightBarButtonItem;
    [rightItem setTintColor:[UIColor blackColor]];
    
    
}

-(void)leftBarButtonItemPressed:(id)sender{

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

@end
