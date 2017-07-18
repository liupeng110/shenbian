//
//  FocusTableViewCell.h
//  shenbianapp
//
//  Created by 杨绍智 on 17/7/15.
//  Copyright © 2017年 杨绍智. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FocusTableViewCell : UITableViewCell
@property (nonatomic,strong)UIImageView * headImage;
@property (nonatomic,strong)UILabel * perName;
@property (nonatomic,strong)LXButton * stateBtn;
@property (nonatomic,strong)UIView * FocusContentView;

@property (nonatomic,strong)UIImageView *FocusContenImage;
@property (nonatomic,strong)UILabel *FocusContenTitle;
@property (nonatomic,strong)UILabel *FocusContenNote;
@property (nonatomic,strong)UIImageView *FocusContenDressLogo;
@property (nonatomic,strong)UILabel *FocusContenDress;
@property (nonatomic,strong)UIImageView *FocusContenNumLogo;
@property (nonatomic,strong)UILabel *FocusContenNum;
@end
