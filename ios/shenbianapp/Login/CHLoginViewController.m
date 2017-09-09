//
//  CHLoginViewController.m
//  shenbianapp
//
//  Created by book on 2017/9/9.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHLoginViewController.h"

@interface CHLoginViewController ()

@property(nonatomic,strong)UIView *contentView;

@end

@implementation CHLoginViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self.view addSubview:self.contentView];
    [self.contentView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.left.right.equalTo(self.view);
        make.height.mas_equalTo(320);
    }];
    
    [self.view bringSubviewToFront:self.backButton];

}

-(UIView *)contentView{
    if (_contentView == nil) {
        _contentView = [UIView new];
        _contentView.backgroundColor = [UIColor colorWithHexString:@"#009698"];
    }
    return _contentView;
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
